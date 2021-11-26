package com.sean.scheduler.service

import com.sean.scheduler.util.AppUtil
import org.slf4j.LoggerFactory
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ScheduledFuture
import kotlin.collections.HashMap

@Service
class Scheduler(
    private val scheduler: TaskScheduler,
    private val appUtil: AppUtil
) {

    private val log = LoggerFactory.getLogger(Scheduler::class.java)

    var jobsMap = HashMap<Long, ScheduledFuture<*>?>()

    @EventListener(ContextRefreshedEvent::class)
    fun contextRefreshedEvent() {
        log.info("aabbcc")
        addTaskToScheduler(1L, "0 0/1 * * * *", appUtil.getBean("TaskOne") as Runnable)
    }

    fun addTaskToScheduler(id: Long, cronStr: String, task:Runnable) {
        if(isTaskRunning(id)) {
            removeTaskFromScheduler(id)
        }
        jobsMap[id] = scheduler.schedule(
            task,
            CronTrigger(cronStr, TimeZone.getTimeZone(TimeZone.getDefault().id))
        )
    }

    fun isTaskRunning(id: Long) =
        jobsMap.entries.any { entry -> entry.key == id && entry.value != null }

    fun removeTaskFromScheduler(id: Long) {
        jobsMap[id]?.apply {
            if(cancel(true)) {
                jobsMap[id] = null
            }
        }
    }
}