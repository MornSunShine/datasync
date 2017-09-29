# datasync
Realize the synchronization of different databases‘ tables，from one table to the other table

## Interface
* DbHelper: 同步目标数据库执行接口，帮助在向目标数据库同步数据的时候，设置制定的SQL语句，以及相关优化，
比如当前Demo中就是装配大量的SQL执行语句，将其连接成一个字符串通过返回值的形式进行传递，可以对该策略进行优化，
以及多种在同步中可能用到的SQL优化方法，索引等
实现类的存放位置默认为：com.maomorn.datasync.dbhelper.impl;

## Configuration
目标数据库、源数据库的基本参数;
作业支持多作业同步执行，类似对源数据进行操作后同步的行为，可以直接在作业中的<srcSql>下做功夫