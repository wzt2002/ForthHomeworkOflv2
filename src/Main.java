import java.util.Scanner;

/*
LV - 2 自由发挥（如果做的好，分数可能比Lv5高）
俗话说：“兴趣是最好的老师”，一门语言的学习是漫长且枯燥的，如果你能找到一个想要做的小游戏
（或者功能），在完成这件事的过程中学习java，那将绝杀（事半功倍hhh）
你的代码中应该尽可能体现迄今为止所学过的java知识（面向对象、封装...）
提供参考选题：
1. 做一个控制台的文字游戏（如扑克牌类，或者剧情类）
2. 实现一个学生成绩管理系统（可以随意增删查改某个学生记录，求成绩最大最小值，如果科目数量
可拓展将加分）
3. 控制台图形小游戏，在控制台输入上下左右控制人物的移动，用控制台输出多行字符串来控制人物
的上下左右之类的，发挥你的想象力
 */
public class Main {
    private Room currentRoom;

    public Main()
    {
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;

        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");

        //	初始化房间的出口
        outside.setExits(null, lobby, study, pub);
        lobby.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        study.setExits(outside, bedroom, null, null);
        bedroom.setExits(null, null, null, study);

        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        System.out.println("现在你在" + currentRoom);
        System.out.print("出口有：");
        if(currentRoom.northExit != null)
            System.out.print("north ");
        if(currentRoom.eastExit != null)
            System.out.print("east ");
        if(currentRoom.southExit != null)
            System.out.print("south ");
        if(currentRoom.westExit != null)
            System.out.print("west ");
        System.out.println();
    }

    // 以下为用户命令

    private void printHelp()
    {
        System.out.print("迷路了吗？你可以做的命令有：go bye help");
        System.out.println("如：\tgo east");
    }

    private void goRoom(String direction)
    {
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("你在" + currentRoom);
            System.out.print("出口有: ");
            if(currentRoom.northExit != null)
                System.out.print("north ");
            if(currentRoom.eastExit != null)
                System.out.print("east ");
            if(currentRoom.southExit != null)
                System.out.print("south ");
            if(currentRoom.westExit != null)
                System.out.print("west ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main game = new Main();
        game.printWelcome();

        while ( true ) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            if ( words[0].equals("help") ) {
                game.printHelp();
            } else if (words[0].equals("go") ) {
                game.goRoom(words[1]);
            } else if ( words[0].equals("bye") ) {
                break;
            }
        }

        System.out.println("感谢您的光临。再见！");
        in.close();
    }

}