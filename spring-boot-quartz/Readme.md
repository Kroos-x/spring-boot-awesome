### Quartz 核心概念:
##### 1.Job:是一个接口,只定义一个方法excute(JobExcutionContext context),可以通过实现该接口来定义需要执行的任务(具体的业务代码)
##### 2.JobDetail: Quartz每次调度Job时，都重新创建一个Job实例。用来描述Job的实现类及其他相关的静态信息
##### 3.Trigger: 作业的定时管理工具，一个 Trigger 只能对应一个作业实例，而一个作业实例可对应多个触发器
##### 4.Scheduler: 定时任务容器，是Quartz最上层的东西，它提携了所有触发器和作业，使它们协调工作。一个Scheduler中可以注册多个JobDetail和多个Trigger

