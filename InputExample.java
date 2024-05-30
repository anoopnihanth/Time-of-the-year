import java.awt.*;
import java.awt.event.*;
public class InputExample extends Frame implements ActionListener {
    TextField textField;
    Button submitButton;
    String ans = "";
    public InputExample() {
        textField = new TextField(20);
        submitButton = new Button("Get date");
        submitButton.addActionListener(this);
        setLayout(new FlowLayout());
        add(textField);
        add(submitButton);
        submitButton.setBackground(Color.CYAN);
        setTitle("Date and the Day");
        setSize(300, 300);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Get date")) {
            String userInput = textField.getText();
            int input = Integer.parseInt(userInput);
            String[] weeks = {"Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday"};
            int[][] arr = {{0, 0}, {1, 31}, {2, 59}, {3, 90}, {4, 120}, {5, 151},
                    {6, 181}, {7, 212}, {8, 243}, {9, 273}, {10, 304}, {11, 334}, {12, 365}};
            int[][] larr = {{0, 0}, {1, 31}, {2, 60}, {3, 91}, {4, 121}, {5, 152},
                    {6, 182}, {7, 213}, {8, 244}, {9, 274}, {10, 305}, {11, 335}, {12, 366}};
            String[] months = {"","JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEPT", "OCT", "NOV", "DEC"};
            int seconds = input;
            if(seconds < 86400) {
                System.out.println("01-JAN-1970 Thursday");
                ans = "01-JAN-1970 Thursday";
            }
            else {
                int days = seconds / 86400;
                boolean leap = false;
                int years = 0, date = 1, month = 0, week = days % 7;
                while((days >= 366 && leap) || (days >= 365 && !leap)) {
                    years++;
                    if(leap) days -= 366;
                    else days -= 365;
                    if(years == 2 || years % 4 == 0) leap = true;
                    else leap = false;
                }
                if(!leap) {
                    if(days < 31) month = 1;
                    else if(days < 59) month = 2;
                    else if(days < 90) month = 3;
                    else if(days < 120) month = 4;
                    else if(days < 151) month = 5;
                    else if(days < 181) month = 6;
                    else if(days < 212) month = 7;
                    else if(days < 243) month = 8;
                    else if(days < 273) month = 9;
                    else if(days < 304) month = 10;
                    else if(days < 334) month = 11;
                    else month = 12;
                }
                else {
                    if(days < 31) month = 1;
                    else if(days < 60) month = 2;
                    else if(days < 91) month = 3;
                    else if(days < 121) month = 4;
                    else if(days < 152) month = 5;
                    else if(days < 182) month = 6;
                    else if(days < 213) month = 7;
                    else if(days < 244) month = 8;
                    else if(days < 274) month = 9;
                    else if(days < 305) month = 10;
                    else if(days < 335) month = 11;
                    else month = 12;
                }
                if(month != 1) {
                    if(!leap) date = days - arr[month - 1][1] + 1;
                    else date = days - larr[month - 1][1] + 1;
                }
                else date = days + 1;
                String day = date / 10 != 0? String.valueOf(date): "0"+date;
                years += 1970;
                ans = day +"-"+ months[month] + "-" + years +" "+ weeks[week];
                System.out.println(ans);
            }
        }
        repaint();
    }
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(30, 100, 250, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        g.drawString(ans, 50, 130);
    }
    public static void main(String[] args) {
        new InputExample();
    }
}
