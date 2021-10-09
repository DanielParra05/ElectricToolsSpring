package com.spring.electric.tools.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.electric.tools.models.entities.Settings;

public interface SettingsRepository  extends CrudRepository<Settings, Long> {
	
}
