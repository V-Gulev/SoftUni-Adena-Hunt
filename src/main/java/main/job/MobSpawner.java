package main.job;


import lombok.extern.slf4j.Slf4j;
import main.model.Mob;
import main.property.MobProperties;
import main.service.MobService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MobSpawner {

    private final MobProperties mobProperties;
    private final MobService mobService;

    public MobSpawner(MobService mobService, MobProperties mobProperties) {
        this.mobService = mobService;
        this.mobProperties = mobProperties;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void spawnMobs() {

        mobProperties.getMobs().forEach(mob -> {
            Mob createdMob = mobService.create(mob);
            log.info("Mob [{}][{}] was spawned.", createdMob.getName(), createdMob.getLevel());
        });

    }
}
