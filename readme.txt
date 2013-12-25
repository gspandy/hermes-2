  系统名称 参考希腊神谱：   【注：-- 表示被占用】
宙斯(Zeus) 天神，希腊神话中最高的神，克洛诺斯和瑞亚的儿子。
赫拉(Hera) 天后，克洛诺斯和瑞亚的长女，宙斯的姐姐和妻子。
-- 哈迪斯(Hades) 冥王，克洛诺斯和瑞亚的儿子，宙斯的兄弟。   
帕尔塞福涅(Persephone) 冥后，宙斯和得墨忒尔的女儿。
波塞冬(Poseidon) 海皇，克洛诺斯和瑞亚的儿子，宙斯的兄弟。
雅典娜(Athena) 智慧女神，女战神，从宙斯的头颅中诞生。
阿波罗(Apollo) 太阳神，宙斯和勒托之子。
阿瑞斯(Ares) 战神，宙斯和赫拉之子。
阿佛洛狄忒(Aphrodite) 爱情女神，宙斯与迪俄涅的女儿。
狄俄尼索斯(Dionysus) 酒神，宙斯和塞墨勒的儿子。
阿尔忒尼斯(Artemis) 月亮和狩猎女神，宙斯和勒托之女，阿波罗孪生姐姐。
赫淮斯托斯(Hephaestus) 火神，宙斯和赫拉之子。
-- 赫尔墨斯(Hermes) 宙斯和迈亚的儿子，众神的使者，亡灵的接引神。
厄俄斯(Eos) 黎明女神
艾莉斯(Eris) 纷争女神
厄洛斯(Eros) 爱神
耐得斯(Naiads) 江河水泉中的女神
卡吕普索(Calypso) 女神
西摩伊斯(Simois) 河神
阿科洛厄斯(Achelous) 河神
阿克西厄斯(Axius) 派厄尼亚河神
格劳克斯(Glaucus) 海神，善作预言
特里同(Triton) 海神
琉科忒亚(Leucothea) 海中女神
涅锐伊得斯(Nereids) 海中女神
盖亚(Gaea) 大地之母
塞勒涅(Selene) 月亮女神
塔那托斯(Thanatus) 死神
许普诺斯(Sleep) 睡神
赫卡忒(Hecaba) 夜和下界女神，亦是幽灵和魔法女神
绪任克斯(Syrinx) 山林女神
潘(Pan) 山林之神
参考资料：http://baike.baidu.com/subview/5952/9571105.htm

＊＊＊＊名称选择需要修改几个部分＊＊＊＊
1、project_name
2、com.zeus.app
3、zeus.tld

＊＊＊＊开发过程项目结构变动指南＊＊＊＊
1、注释需要空格 如下：
// 我前面有个空格，双斜杠后面。

2、数据库部分对象比较多情况下（domian、persistence和service）
多余3个对象的模块建立一个package：
domain.mysql.user.xx
domain.oracle.bill.xx

persistence..

service..

3、因为用了mybatis，在对应的resources目录下有几个xml文件，对应的对象路径要改下。

4、web.xml修改：改变配置文件初始化位置。删除ContextLoaderListener让其正常启动~

5、自定义标签路径