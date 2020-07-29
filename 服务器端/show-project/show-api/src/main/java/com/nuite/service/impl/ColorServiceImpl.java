package com.nuite.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.dao.ColorDao;
import com.nuite.entity.ColorEntity;
import com.nuite.service.ColorService;


@Service
public class ColorServiceImpl extends ServiceImpl<ColorDao, ColorEntity> implements ColorService {

}
