### 玩家数据存储方式：

yml.userNames: 存储所有用户 --List[String]

yml.users.xxx: 存储某一种操作的目前用户 --List[String]

    每次有玩家加入时或者申请存储时会增加用户，此外在运行时不会有任何删除行为

yml.usercaches.xxx: 存储某一种操作需要保存的字符串数据 --String

    可以通过set和del修改