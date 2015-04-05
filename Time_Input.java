import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class Time_Input implements ActionListener
{
    JFrame frame = new JFrame("Enter the countdown seconds");
    JButton but[]=new JButton[10];
    JTextField field = new JTextField(20);
    JPanel panel = new JPanel();
    JPanel p2 = new JPanel();
    JButton close = new JButton("Close");
    JButton clear = new JButton("Clear");
    JButton bksp = new JButton("Backspace");
    int time = 60;
    public void run()
    {
        panel.setLayout(new GridLayout(4,3));
        frame.getContentPane().add(panel,BorderLayout.CENTER);
        frame.getContentPane().add(field,BorderLayout.NORTH);
        frame.getContentPane().add(p2,BorderLayout.SOUTH);
        p2.add(close);
        
        frame.setSize(640,480);
        frame.setVisible(false);
        field.setEditable(false);
        close.addActionListener(this);        
        for(int i=0;i<10;i++)
        {
            but[i]=new JButton(""+i);
            panel.add(but[i]);
            but[i].addActionListener(this);
        }
        panel.add(clear);panel.add(bksp);
        clear.addActionListener(this);
        bksp.addActionListener(this);
    }
    public void show()
    {
	frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        for(int i=0;i<10;i++)
        {
            if(but[i]==e.getSource())
            {
                field.setText(field.getText()+but[i].getText());
                time=Integer.parseInt(field.getText());
            }
            if(field.getText().length()==0)
            bksp.setEnabled(false);
            else
            bksp.setEnabled(true);
        }        
        if(clear==e.getSource())
        {
            field.setText("");
            if(field.getText().length()==0)
            bksp.setEnabled(false);
            else
            bksp.setEnabled(true);
        }
        if(bksp==e.getSource())
        {
            if(field.getText().length()==0)
            bksp.setEnabled(false);
            else
            bksp.setEnabled(true);
            field.setText(field.getText().substring(0,field.getText().length()-1));
            if(field.getText().length()==0)
            bksp.setEnabled(false);
            else
            bksp.setEnabled(true);
        }
        if(close==e.getSource())
        frame.setVisible(false);
    }
}