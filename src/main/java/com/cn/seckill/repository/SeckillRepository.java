package com.cn.seckill.repository;

import com.cn.seckill.model.Seckill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeckillRepository extends JpaRepository<Seckill, Long> {
	
	
}
