/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.chenjw.imagegrab.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chenjw.imagegrab.service.ImagegrabService;


public class MainFrame extends JFrame {

    /**  */
    private static final long serialVersionUID = 1810483835021058608L;
    private JScrollPane       resultScrollPane;
    private JButton           downloadButton;
    private JLabel            jLabel1;

    private HistoryComboBox   searchWordComboBox;
    private JTextArea         resultPane;
    private JPanel            configPanel      = new JPanel();
    private ImagegrabService  imagegrabService;
    private HistoryComboBox grayThresholdComboBox;
    private JLabel            jLabel6;
    private JLabel            jLabel3;
    private HistoryComboBox   sourceComboBox;
    // 用于异步执行任务
    private ExecutorService   executeService   = Executors.newSingleThreadExecutor();

    private void initSpring() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath*:grab-tool.xml");
        imagegrabService= (ImagegrabService) ctx.getBean("imagegrabService");
       System.out.println("spring inited!");
       resultPane.append("启动成功！\n");
       imagegrabService.setDataHandler(new DataHandler() {

            @Override
            public void appendResult(String text) {
                resultPane.append(text);
                resultPane.setCaretPosition(resultPane.getText().length());
            }

            @Override
            public void clearResult() {
                resultPane.setText(null);
            }

            @Override
            public String getSearchWord() {
                return searchWordComboBox.getText();
            }

            @Override
            public String getSource() {
                return sourceComboBox.getText();
            }

            @Override
            public String getGrayThreshold() {
                return grayThresholdComboBox.getText();
            }

        });
       
    }

    public MainFrame() {
        {
            this.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }

            });
        }

        GroupLayout configPanelLayout = new GroupLayout((JComponent)configPanel);
        configPanel.setLayout(configPanelLayout);
        configPanel.setPreferredSize(new java.awt.Dimension(1035, 726));
        {
            downloadButton = new JButton();
            downloadButton.setText("下载");
            downloadButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    executeService.execute(new Runnable() {
                        @Override
                        public void run() {
                            imagegrabService.grab();
                        }
                    });

                }

            });
            downloadButton.registerKeyboardAction(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    executeService.execute(new Runnable() {
                        @Override
                        public void run() {
                            imagegrabService.grab();
                        }
                    });
                }
            }, KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        }
        {
            jLabel1 = new JLabel();
            jLabel1.setText("搜索关键词");
        }
        {
            resultPane = new JTextArea();
            resultScrollPane = new JScrollPane(resultPane);
            resultScrollPane.setViewportView(resultPane);
        }
        {
            sourceComboBox = new HistoryComboBox("testGroup");
        }
        {
            ComboBoxModel historyComboBox1Model = 
                    new DefaultComboBoxModel(
                        new String[] { "Item One", "Item Two" });
            grayThresholdComboBox = new HistoryComboBox("testGroup");
            grayThresholdComboBox.setModel(historyComboBox1Model);
        }
        {
            jLabel6 = new JLabel();
            jLabel6.setText("灰度阀值");
        }
        {
            jLabel3 = new JLabel();
            jLabel3.setText("来源");
        }

        {
            searchWordComboBox = new HistoryComboBox("samplePath");
        }
        configPanelLayout.setVerticalGroup(configPanelLayout.createSequentialGroup()
            .addGroup(configPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addComponent(searchWordComboBox, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addComponent(downloadButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addComponent(sourceComboBox, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addComponent(grayThresholdComboBox, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(resultScrollPane, 0, 686, Short.MAX_VALUE)
            .addContainerGap());
        configPanelLayout.setHorizontalGroup(configPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(configPanelLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.LEADING, configPanelLayout.createSequentialGroup()
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchWordComboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sourceComboBox, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                    .addGap(24)
                    .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(grayThresholdComboBox, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                    .addGap(34)
                    .addComponent(downloadButton, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 235, Short.MAX_VALUE))
                .addGroup(configPanelLayout.createSequentialGroup()
                    .addComponent(resultScrollPane, GroupLayout.PREFERRED_SIZE, 979, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap());
        //添加其他组件
        pack();
        this.setSize(1010, 768);
        setVisible(true);
        this.setTitle("图片抓取");
        getContentPane().add(configPanel, BorderLayout.CENTER);
        initSpring();
    }
}
