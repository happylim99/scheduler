package com.sean.scheduler.task

import com.sean.scheduler.service.Master
import com.sean.scheduler.service.MasterScheduler
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Component("TaskOne")
class TaskOne: MasterScheduler(), Runnable, Master {
    override fun run() {
        println("${Date()} runnable here")
    }

}