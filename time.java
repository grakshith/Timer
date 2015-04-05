import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.applet.*;
public class time  implements ActionListener
{
    JFrame frame = new JFrame("Timer");
    JButton but = new JButton("Count Down");
    JButton but2 = new JButton("Count Up");
    JButton b1 = new JButton("");
    JButton reset = new JButton("Reset");
    JButton config = new JButton("Configure");
    JPanel panel = new JPanel();
    JButton pause = new JButton("Pause");
    JButton read = new JButton("Read Values");
    Dimension dim = new Dimension();
    Point p = new Point();
    long delay=1000;
    long period = 1000;
    Timer timer = new Timer();
    Timer timer1 = new Timer();
    Font font = new Font("DS-Digital",Font.BOLD,100);
    String pr = "Resume";
    Time_Input obj = new Time_Input();
    int timeconfig,currentTime;  

    public time()
    {
        timeconfig=0;
        currentTime=0;
        dim.height=360;
        dim.width=480;        
    }
    public void r(final int a)
    {
        timer.schedule(new TimerTask()
        {
        int i=a;
        public void run()
        {
            if(i>0)
            {   
                i--;currentTime=i;
                b1.setText(""+i/60+":"+i%60);
            }            
        }
    },delay,period);    
    }
    public void r1()
    {
        timer1.schedule(new TimerTask(){
            int i=0;
            public void run()
            {
                i++;
                b1.setText(""+i/3600+":"+i/60+":"+i%60);
            }
    },delay,period);
    }
    public void m()
    {
        //frame.setLayout(new BorderLayout());
        frame.setSize(dim.width,dim.height);
        frame.setLocation(p);
        frame.setVisible(true);
        //b1.setEnabled(false);
        pause.setEnabled(false);
        frame.getContentPane().add(b1,  BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(but);
        panel.add(but2);
        panel.add(reset);
        frame.getContentPane().add(pause,BorderLayout.NORTH);
        but.addActionListener(this);
        but2.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.setFont(font);
        reset.addActionListener(this);
        pause.addActionListener(this);
        config.addActionListener(this);
        panel.add(config);
        //read.addActionListener(this);
        //panel.add(read);
        timeconfig=60;
        obj.run();
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==config)
        {
            obj.show();        
        }
        if(e.getSource()==pause)
        {
            if(pause.getText().equals("Pause"))
            {
                pause.setText("Resume");
                timer.cancel();                
            }
            else if(pause.getText().equals("Resume"))
            {
                this.dim=frame.getSize();
                this.p=frame.getLocationOnScreen();
                frame.setVisible(false);
                pause.setText("Pause");
                String s = b1.getText();
                int a = currentTime;
                obj.time=a;
                time newtime = new time();
                newtime.timeconfig=a;
                newtime.dim=this.dim;
                newtime.p=this.p;
                newtime.m();
                newtime.b1.setText(s);
                newtime.r(a);
                newtime.but.setEnabled(false);
                newtime.config.setEnabled(false);
                newtime.but2.setEnabled(false);
                newtime.pause.setEnabled(true);
            }
        }
        if(e.getSource()==but2)
        {
            but.setEnabled(false);
            but2.setEnabled(false);
            pause.setEnabled(false);
            config.setEnabled(false);
            r1();
        }
        if(e.getSource()==but)
        {
            but2.setEnabled(false);
            but.setEnabled(false);
            pause.setEnabled(true);
            config.setEnabled(false);
            timeconfig = obj.time;
            r(timeconfig);        
        }
        if(e.getSource()==reset)
        {
            frame.setVisible(false);
            new time().m();
        }
        /*if(e.getSource()==read)
        {
            
        }*/
    }
    public static void main(String args[])
    {
        time t = new time();        
        t.m();
    }
}