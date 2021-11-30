package com.sean.scheduler.service.impl

import com.sean.scheduler.service.MasterSchedulerSrv
import com.sean.scheduler.task.MasterTask
import com.sean.scheduler.util.AppUtil
import org.springframework.stereotype.Service

@Service
class MasterSchedulerSrvImpl(
    private val appUtil: AppUtil
): MasterSchedulerSrv {

    override fun showAll() = appUtil.getAllBean(MasterTask::class.java)
}