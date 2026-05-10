import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.*;
import java.util.Base64;

public class Mainframe extends JFrame{
    private JTextAreaPlus mainScreen = new JTextAreaPlus();
    private JButton option1 = new JButton();
    private JButton option2 = new JButton();
    private JButton option3 = new JButton();
    private JButton option4 = new JButton();
    private JButton exit = new JButton();
    private JButton[] numbers = new JButton[10];
    private JButton clear = new JButton();
    private JButton cancel = new JButton();
    private JButton enter = new JButton();
    private ImageIcon logo = new ImageIcon("src/images/uob.png");  // Change the Path if it is required.
    private ImageIcon background = new ImageIcon("src/images/Background.jpg");  // Change the Path if it is required.
    private JLabel Logo = new JLabel(logo);
    private String userInput = "";
    private Font font = new Font("Arial",Font.BOLD,15);
    private int currentState = 1;
    private int language = 1;
    private boolean enableNum = false;
    private Account currentAccount; //userAccount
    private Account receiverAccount;
    private boolean isProtected = false;
    private File userAccountFile;
    private File receiverAccountFile;

    public Mainframe()
    {
        String dirName = "./src/accounts";
        Path dirPath = Paths.get(dirName);
        if (!Files.exists(dirPath)) {
            try{
                Files.createDirectory(dirPath);
            }
            catch(Exception e){
                System.out.println("Failed to create directory");
            }
        }
        //Set up main screen
        mainScreen.setBounds(200,20,600,450);
        mainScreen.setFont(font);
        mainScreen.setText("Choose your Language.\n\n Option 1. English\n Option 2. 한국어 \n Option 3. 中文 ");
        mainScreen.setEditable(false);
        mainScreen.setImage(background);
        mainScreen.setForeground(Color.WHITE);
        mainScreen.setMargin(new Insets(15,10,10,10));
        add(mainScreen);

        //Set up and set functions for the option 1 button
        option1.setBounds(815,15,130,80);
        option1.setText("Option 1");
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currentState == 1)
                {
                        language =1 ;
                        currentState = 2;
                        enableNum = true;
                        mainScreen.setText("Please insert your card (number): ");
                }
                if(currentState == 4)
                {
                    if(language == 1)
                    {
                        mainScreen.setText("Your balance is " + String.valueOf(currentAccount.getBalance()) + "\nPlease enter the amount: ");
                    }
                    else if(language == 2)
                    {
                        mainScreen.setText("귀하의 잔액은 " + String.valueOf(currentAccount.getBalance()) + "\n금액을 입력하세요: ");
                    }
                    else if(language == 3)
                    {
                        mainScreen.setText("您的余额是 " + String.valueOf(currentAccount.getBalance()) + "\n请输入金额： ");
                    }
                    enableNum = true;
                    currentState = 5;
                }
            }
        });
        add(option1);

        //Set up and set functions for the option 2 button
        option2.setBounds(815,110,130,80);
        option2.setText("Option 2");
        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currentState == 1)
                {
                    language = 2;
                    currentState = 2;
                    enableNum = true;
                    mainScreen.setText("카드를 삽입해주세요 (번호):");
                }
                if(currentState == 4)
                {
                    if(language == 1)
                    {
                        mainScreen.setText("Your balance is " + String.valueOf(currentAccount.getBalance()) + "\nPlease enter the amount: ");
                    }
                    else if(language == 2)
                    {
                        mainScreen.setText("귀하의 잔액은 " + String.valueOf(currentAccount.getBalance()) + "\n금액을 입력하세요: ");
                    }
                    else if(language == 3)
                    {
                        mainScreen.setText("您的余额是 " + String.valueOf(currentAccount.getBalance()) + "\n请输入金额： ");
                    }
                    enableNum = true;
                    currentState = 7;

                }
            }
        });
        add(option2);

        //Set up and set functions for the option 3 button
        option3.setBounds(815,205,130,80);
        option3.setText("Option 3");
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currentState == 1)
                {
                    language = 3;
                    currentState = 2;
                    enableNum = true;
                    mainScreen.setText("请输入你的卡片（号码）：");
                }
                if(currentState == 4)
                {
                    if(language == 1)
                    {
                        mainScreen.setText("Enter receiver's card number: ");
                        enableNum = true;
                        currentState = 9;
                    }
                    if(language == 2)
                    {
                        mainScreen.setText("수신자의 카드번호를 입력해주세요: ");
                        enableNum = true;
                        currentState = 9;
                    }
                    if(language == 3)
                    {
                        mainScreen.setText("输入收款人卡号： ");
                        enableNum = true;
                        currentState = 9;
                    }
                }
            }

        });
        add(option3);

        //Set up and set functions for the option 4 button
        option4.setBounds(815,300,130,80);
        option4.setText("Option 4");
        add(option4);
        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currentState == 4)
                {
                    if(language == 1)
                    {
                        currentState = 12;
                        enableNum = false;
                        mainScreen.setText("Your current balance is " + currentAccount.getBalance() + "\n\n Press Enter to continue");
                    }
                    if(language == 2)
                    {
                        currentState = 12;
                        enableNum = false;
                        mainScreen.setText("귀하의 현재 잔액은 " + currentAccount.getBalance() + "\n\n 계속하려면 Enter를 누르세요");
                    }
                    if(language == 3)
                    {
                        currentState = 12;
                        enableNum = false;
                        mainScreen.setText("您当前的余额是 " + currentAccount.getBalance() + "\n\n 按 Enter 继续");
                    }
                }
            }
        });

        //Set up number buttons
        for(int i = 0; i < 10; i++)
        {

            numbers[i] = new JButton();
            if(i == 0)
            {
                numbers[i].setBounds(390,710,90,70);
                numbers[i].setFont(new Font("Arial",Font.BOLD,20));
                numbers[i].setText("0");
            }
            else
            {
                numbers[i].setBounds(295+((i+2)%3)*95,485+((i-1)/3*75),90,70);
                numbers[i].setFont(new Font("Arial",Font.BOLD,20));
                numbers[i].setText(Integer.toString(i));
            }
            int finalI = i;
            numbers[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(enableNum == true)
                    {
                        if(isProtected == true)
                        {
                            mainScreen.setText(mainScreen.getText() + "*");
                        }
                        else
                        {
                            mainScreen.setText(mainScreen.getText() + Integer.toString(finalI));
                        }
                        userInput = userInput + String.valueOf(finalI);
                    }
                }
            });
            add(numbers[i]);
        }

        //Set up clear button
        clear.setBounds(595,485,110,70);
        clear.setContentAreaFilled(true);
        clear.setOpaque(true);
        clear.setBorderPainted(false);
        clear.setFocusPainted(true);
        clear.setBackground(new Color(255, 195, 0 ));
        clear.setFont(new Font("Arial",Font.BOLD,15));
        clear.setText("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(enableNum == true)//Only in the page that the user can type numbers
                {
                    userInput = ""; //reset the value of userInput
                    if(currentState == 5 | currentState == 7) // This is when numbers, apart from the ones that the user wrote, appears on the screen
                    {
                        String[] currentText = mainScreen.getText().split("\n",2); // split the text shown on the screen based on \n
                        mainScreen.setText(currentText[0] + "\n" + currentText[1].replaceAll("[0-9]", "")); // only remove the numbers that the user typed
                    }
                    else // for the rest of the pages, where there are no numbers apart from the ones that the user wrote
                    {
                        mainScreen.setText(mainScreen.getText().replaceAll("[0-9]|[*]", "")); // remove all numbers on the screen
                    }
                }
            }
        });
        add(clear);

        //Set up cancel button
        cancel.setBounds(595,485 + 75,110,70);
        cancel.setContentAreaFilled(true);
        cancel.setOpaque(true);
        cancel.setBorderPainted(false);
        cancel.setFocusPainted(true);
        cancel.setBackground(new Color(239, 81, 22));
        cancel.setFont(new Font("Arial",Font.BOLD,15));
        cancel.setText("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(enableNum == true&&!userInput.equals(""))
                {
                    userInput = userInput.substring(0, userInput.length() - 1);
                    mainScreen.setText(mainScreen.getText().substring(0,mainScreen.getText().length()-1));
                }
            }
        });
        add(cancel);

        //Set up enter button
        enter.setBounds(595,485 + 75 * 2,110,70);
        enter.setContentAreaFilled(true);
        enter.setOpaque(true);
        enter.setBorderPainted(false);
        enter.setFocusPainted(true);
        enter.setBackground(new Color(88, 220, 39));
        enter.setFont(new Font("Arial",Font.BOLD,15));
        enter.setText("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currentState == 2 && enableNum == true)// Checking card #
                {
                    int checkAccount = 0;
                    File[] files = new File("./src/accounts").listFiles();
                    for(File account : files)
                    {
                        String accountNumber = account.getName().substring(0, account.getName().length() - 4);
                        if(accountNumber.equals(userInput))
                        {
                            try {
                                BufferedReader br = new BufferedReader(new FileReader(account));
                                String[] userInfo = new String[4];
                                int counter = 0;
                                while ((userInfo[counter++] = br.readLine()) != null){}
                                for(int i = 0; i < 3; i++){
                                    userInfo[i] = decode(userInfo[i]);
                                }
                                currentAccount = new Account(userInfo[0], accountNumber, userInfo[1], Long.parseLong(userInfo[2]));
                                if(language == 1)
                                {
                                    mainScreen.setText("Please enter your password: ");
                                }
                                if(language == 2)
                                {
                                    mainScreen.setText("비밀번호를 입력하세요: ");
                                }
                                if(language == 3)
                                {
                                    mainScreen.setText("请输入您的密码： ");
                                }
                                currentState = 3;
                                isProtected = true;
                                checkAccount = 1;
                                userAccountFile = account;
                            }
                            catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    if(checkAccount == 0)
                    {
                        if(language == 1)
                        {
                            mainScreen.setText("Incorrect Card(Number)\nPlease insert your card (number): ");
                        }
                        if(language == 2)
                        {
                            mainScreen.setText("잘못된 카드(번호)\n카드(번호)를 입력해주세요: ");
                        }
                        if(language == 3)
                        {
                            mainScreen.setText("卡号（号码）错误\n请插入您的卡（号码）： ");
                        }

                    }
                    userInput = "";

                }
                else if(currentState == 3)
                {
                    if(currentAccount.getPassword().equals(userInput))//Mainpage
                    {
                        if(language == 1)
                        {
                            mainScreen.setText("Welcome " + currentAccount.getName() + "!\n\nPlease choose your option\n\nOption 1. Withdraw\nOption 2. Deposit\nOption 3. Transfer\nOption 4. Balance Check");
                        }
                        else if(language == 2)
                        {
                            mainScreen.setText("환영합니다 " + currentAccount.getName() + "!\n\n옵션을 선택해주세요\n\nOption 1. 출금\nOption 2. 예금\nOption 3. 송금\nOption 4. 예금 조회");
                        }
                        else if(language == 3)
                        {
                            mainScreen.setText("欢迎 " + currentAccount.getName() + "!\n\n请选择您的选项\n\nOption 1. 提取\nOption 2. 订金\nOption 3. 汇款\nOption 4. 存款查询");
                        }
                        enableNum = false;
                        currentState = 4;
                        isProtected = false;
                    }
                    else
                    {
                        if(language == 1)
                        {
                            mainScreen.setText("Wrong Password\nPlease enter your password: ");
                        }
                        else if(language == 2)
                        {
                            mainScreen.setText("잘못된 비밀번호입니다\n비밀번호를 입력해주세요: ");
                        }
                        else if(language == 3)
                        {
                            mainScreen.setText("密码错误\n请输入密码: ");
                        }
                    }
                    userInput = "";
                }
                else if(currentState == 5)//Withdraw
                {
                    if(Long.parseLong(userInput) > currentAccount.getBalance())
                    {
                        if(language == 1)
                        {
                            mainScreen.setText("Not enough Balance\n\nYour Balance is " + String.valueOf(currentAccount.getBalance()) + "\nPlease enter your amount: ");
                        }
                        else if(language == 2)
                        {
                            mainScreen.setText("잔액이 충분하지 않습니다\n\n당신의 잔액은 " + String.valueOf(currentAccount.getBalance()) + "\n금액을 입력해주세요: ");
                        }
                        else if(language == 3)
                        {
                            mainScreen.setText("余额不足\n\n您的余额是" + String.valueOf(currentAccount.getBalance()) + "\n请输入您的金额： ");
                        }
                    }
                    else
                    {
                        currentState = 6;
                        currentAccount.setBalance(currentAccount.getBalance() - Long.parseLong(userInput));
                        if(language == 1)
                        {
                            mainScreen.setText("Thank you for using!\nYour balance is now " + currentAccount.getBalance()
                                    + "\n\n Press Enter to continue");
                        }
                        if(language == 2)
                        {
                            mainScreen.setText("이용해주셔서 감사합니다!\n당신의 잔액은 현재 " + currentAccount.getBalance()
                                    + "\n\n 계속하려면 Enter를 누르세요");
                        }
                        if(language == 3)
                        {
                            mainScreen.setText("感谢您的使用！\n您现在的余额是 " + currentAccount.getBalance()
                                    + "\n\n 按 Enter 继续");
                        }
                        enableNum = false;
                        try {
                            FileWriter myWriter = new FileWriter(userAccountFile);
                            myWriter.write(encode(currentAccount.getName()) + "\n" + encode(currentAccount.getPassword())
                                    + "\n" + encode(String.valueOf(currentAccount.getBalance())));
                            myWriter.close();
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                    }
                    userInput = "";
                }
                else if(currentState == 7)//Deposit
                {
                    currentAccount.setBalance(currentAccount.getBalance() + Long.parseLong(userInput));
                    if(language == 1)
                    {
                        mainScreen.setText("Thank you for using!\nYour balance is now " + currentAccount.getBalance() + "\n\n Press Enter to continue");
                    }
                    if(language == 2)
                    {
                        mainScreen.setText("이용해주셔서 감사합니다!\n당신의 잔액은 현재 " + currentAccount.getBalance() + "\n\n 계속하려면 Enter를 누르세요");
                    }
                    if(language == 3)
                    {
                        mainScreen.setText("感谢您的使用！\n您现在的余额是 " + currentAccount.getBalance() + "\n\n 按 Enter 继续");
                    }
                    enableNum = false;
                    currentState = 8;
                    try {// Changing balance in text file
                        FileWriter myWriter = new FileWriter(userAccountFile);
                        myWriter.write(encode(currentAccount.getName()) + "\n" + encode(currentAccount.getPassword()) + "\n" + encode(String.valueOf(currentAccount.getBalance())));
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                }
                else if(currentState == 9)//Transfer
                {
                    int receiveAcc = 0;
                    File[] files = new File("./src/accounts").listFiles();
                    for(File account : files)
                    {
                        String accountNumber = account.getName().substring(0, account.getName().length()-4);
                        if(accountNumber.equals(userInput))
                        {
                            try {//reading reciever's account
                                BufferedReader br = new BufferedReader(new FileReader(account));
                                String[] userInfo = new String[4];
                                int counter = 0;
                                while ((userInfo[counter++] = br.readLine()) != null){}
                                for(int i = 0; i < 3; i++)
                                {
                                    userInfo[i] = decode(userInfo[i]);
                                }
                                receiverAccount = new Account(userInfo[0], accountNumber, userInfo[1], Long.parseLong(userInfo[2]));
                                receiveAcc = 1;
                                if (language == 1)
                                {
                                    mainScreen.setText("Please enter the amount: ");
                                }
                                else if (language == 2)
                                {
                                    mainScreen.setText("금액을 입력하세요: ");
                                }
                                else if (language == 3)
                                {
                                    mainScreen.setText("请输入您的金额： ");
                                }
                                currentState = 10;
                                receiverAccountFile = account;
                            }
                            catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    if(receiveAcc == 0)
                    {
                        if (language == 1)
                        {
                            mainScreen.setText("Wrong card number\nPlease enter receiver's card number: ");
                        }
                        else if (language == 2)
                        {
                            mainScreen.setText("잘못된 카드 번호입니다\n올바른 수신자의 카드 번호를 입력해주세요: ");
                        }
                        else if (language == 3)
                        {
                            mainScreen.setText("卡号错误\n请输入正确的收件人卡号： ");
                        }
                    }
                    userInput = "";
                }
                else if(currentState == 10)
                {
                    if(currentAccount.getBalance() < Long.parseLong(userInput))
                    {
                        if (language == 1)
                        {
                            mainScreen.setText("Not enough balance!\nPlease enter the amount: ");
                        }
                        else if (language == 2)
                        {
                            mainScreen.setText("잔액이 부족합니다!\n금액을 입력하세요: ");
                        }
                        else if (language == 3)
                        {
                            mainScreen.setText("您的余额不足！\n请输入您的金额： ");
                        }
                    }
                    else
                    {
                        currentAccount.setBalance(currentAccount.getBalance() - Long.parseLong(userInput));
                        receiverAccount.setBalance(receiverAccount.getBalance() + Long.parseLong(userInput));
                        if (language == 1)
                        {
                            mainScreen.setText("Your balance is now " + String.valueOf(currentAccount.getBalance()) + "\n\nPress Enter to continue");
                        }
                        else if (language == 2)
                        {
                            mainScreen.setText("당신의 현재 잔액은 " + String.valueOf(currentAccount.getBalance()) + "\n\n계속하려면 Enter를 누르세요");
                        }
                        else if (language == 3)
                        {
                            mainScreen.setText("您现在的余额是 " + String.valueOf(currentAccount.getBalance()) + "\n\n按 Enter 继续");
                        }
                        currentState = 11;
                        try {//changing balance in the user text file
                            FileWriter myWriter = new FileWriter(userAccountFile);
                            myWriter.write(encode(currentAccount.getName()) + "\n"+encode(currentAccount.getPassword()) + "\n" + encode(String.valueOf(currentAccount.getBalance())));
                            myWriter.close();
                            // changing balance in the receiver text file
                            myWriter = new FileWriter(receiverAccountFile);
                            myWriter.write(encode(receiverAccount.getName()) + "\n" + encode(receiverAccount.getPassword()) + "\n" + encode(String.valueOf(receiverAccount.getBalance())));
                            myWriter.close();

                        }
                        catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                    }
                    userInput = "";
                }
                else if(currentState == 6 | currentState == 8 | currentState == 11 | currentState == 12)
                {
                    currentState = 4;
                    if(language == 1)
                    {
                        mainScreen.setText("Welcome " + currentAccount.getName() + "!\n\nPlease choose your option\n\nOption 1. Withdraw\nOption 2. Deposit\nOption 3. Transfer\nOption 4. Balance Check");
                    }
                    else if(language == 2)
                    {
                        mainScreen.setText("환영합니다 " + currentAccount.getName() + "!\n\n옵션을 선택해주세요\n\nOption 1. 출금\nOption 2. 예금\nOption 3. 송금\nOption 4. 예금 조회");
                    }
                    else if(language == 3)
                    {
                        mainScreen.setText("欢迎 " + currentAccount.getName() + "!\n\n请选择您的选项\n\nOption 1. 提取\nOption 2. 订金\nOption 3. 汇款\nOption 4. 存款查询");
                    }
                    enableNum = false;
                }

            }
        });
        add(enter);

        //Set up exit
        exit.setBounds(815, 395, 130, 80);
        exit.setFont(new Font("Arial",Font.BOLD,15));
        exit.setText("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currentState == 6 | currentState == 8 |currentState == 11 | currentState == 12)
                {
                    mainScreen.setText("Choose your Language.\n\nOption 1. English\nOption 2. 한국어 \nOption 3. 中文 ");
                    enableNum = false;
                    currentState = 1;
                }
                else if(currentState > 4)
                {
                    if(language == 1)
                    {
                        mainScreen.setText("Welcome " + currentAccount.getName() + "!\n\nPlease choose your option\n\nOption 1. Withdraw\nOption 2. Deposit\nOption 3. Transfer\nOption 4. Balance Check");
                    }
                    else if(language == 2)
                    {
                        mainScreen.setText("환영합니다 " + currentAccount.getName() + "!\n\n옵션을 선택해주세요\n\nOption 1. 출금\nOption 2. 예금\nOption 3. 송금\nOption 4. 예금 조회");
                    }
                    else if(language == 3)
                    {
                        mainScreen.setText("欢迎 " + currentAccount.getName() + "!\n\n请选择您的选项\n\nOption 1. 提取\nOption 2. 订金\nOption 3. 汇款\nOption 4. 存款查询");
                    }
                    enableNum = false;
                    currentState = 4;
                }
                else if(currentState <= 4)
                {
                    mainScreen.setText("Choose your Language.\n\nOption 1. English\nOption 2. 한국어 \nOption 3. 中文 ");
                    enableNum = false;
                    currentState = 1;
                }
            }
        });
        add(exit);

        Logo.setBounds(20,0,140,100);
        add(Logo);

        setTitle("UOB ATM");
        setBackground(new Color(128, 139, 150 ));
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);

    }
    // encode function
    public String encode(String str){
        String encodedStr = Base64.getEncoder().encodeToString(str.getBytes()); // encode the given string using base64 encoder
        return encodedStr;
    }
    // decode function
    public String decode(String encodedStr){
        String decodedStr = new String(Base64.getDecoder().decode(encodedStr)); // decode the given encoded string using base64 decoder
        return decodedStr;
    }
    public static void main(String[] args) {
        new Mainframe();
    }
}