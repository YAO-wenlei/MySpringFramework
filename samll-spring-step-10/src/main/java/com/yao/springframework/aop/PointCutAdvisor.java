package com.yao.springframework.aop;

public interface PointCutAdvisor extends Advisor{
    PointCut getPointCut();
}
