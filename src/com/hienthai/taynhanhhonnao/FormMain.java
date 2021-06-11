/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienthai.taynhanhhonnao;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author hienx
 */
public class FormMain extends javax.swing.JFrame {

    private double a, b, cong, tru, nhan, checkCauHoi;
    int mucdo, thoiGian;
    private double chia;
    private Timer t;
    private boolean gameOver;
    private int diemBestMoi = 0, diemBestCu = 0;
    private int delay = 50;
    private JLabel lblHinhNenCauHoi;
    private LayoutManager overlay;
    File_Manager f;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    DefaultTableModel model;
    public static final String Song_True = "SongTrue.wav";
    public static final String Song_False = "SongFalse.wav";

    public FormMain() {
        initComponents();
        ImageIcon icon = new ImageIcon("cursor.png");
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setFocusable(true);
        f = new File_Manager();
        XuLy_Boder_Button();
        XuLy_Boder_panel();

        XuLy_Key_Left_Right();
        Xuly_HinhNenCauHoi();
        Change_Cursor();

        //---------------------------------------------------------------------------------
        setTable();
        model = (DefaultTableModel) tableDiemCao.getModel();

        docFileVaoTable();
        //tableDiemCao.setAutoCreateRowSorter(true);

    }

    private void setTable() {
        JTableHeader header = tableDiemCao.getTableHeader();
        header.setBackground(Color.RED);
        header.setForeground(Color.decode("#FF4200"));
        header.setFont(new Font("Tahome", Font.BOLD, 20));
        tableDiemCao.setDefaultEditor(Object.class, null); // không cho sửa giá trị trong table
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER); // căn giữa header
        tableDiemCao.setFont(new Font("Tahome", Font.BOLD, 15));
        // căn giữa nội dung body
        int colCount = tableDiemCao.getColumnCount();
        for (int i = 0; i < colCount; i++) {
            DefaultTableCellRenderer centeRenderer = new DefaultTableCellRenderer();
            centeRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableDiemCao.getColumnModel().getColumn(i).setCellRenderer(centeRenderer);
        }
    }

    private void Change_Cursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("cursor.png");
        Point point = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(image, point, "cursor");
        setCursor(cursor);
    }

    private void Xuly_HinhNenCauHoi() {
        /*-------------------xử lý hình nền câu hỏi ----------------------------------------------------*/
        overlay = new OverlayLayout(panelCauhoi);
        panelCauhoi.setLayout(overlay);
        lblCauHoi.setAlignmentX(0.5f);
        lblCauHoi.setAlignmentY(0.5f);
        panelCauhoi.add(lblCauHoi);
        lblHinhNenCauHoi = new JLabel(new ImageIcon("lblhinnencauhoi.png"));
        lblHinhNenCauHoi.setAlignmentX(0.5f);
        lblHinhNenCauHoi.setAlignmentY(0.5f);
        lblHinhNenCauHoi.setSize(1144, 306);
        panelCauhoi.add(lblHinhNenCauHoi);
    }

    private void XuLy_Key_Left_Right() {

        //------------- sự kiện đúng sai ---------------------------------
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_LEFT) {
                    Dung();
                }
                if (code == KeyEvent.VK_RIGHT) {
                    Sai();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void XuLy_Boder_panel() {
        panelKetQua.setOpaque(false);
        panelCauhoi.setOpaque(false);
    }

    //xay dung tro choi tay nhanh hon nao
    private void XuLy_Boder_Button() {
        /*-------------- xử lý button nhấn xuống không bị lòi khung--------------------------------------*/
        btnHuongdan.setOpaque(false);
        btnHuongdan.setContentAreaFilled(false);
        btnHuongdan.setBorderPainted(false);
        btnChoi.setOpaque(false);
        btnChoi.setContentAreaFilled(false);
        btnChoi.setBorderPainted(false);
        btnDiemCao.setOpaque(false);
        btnDiemCao.setContentAreaFilled(false);
        btnDiemCao.setBorderPainted(false);
        btnThoat.setOpaque(false);
        btnThoat.setContentAreaFilled(false);
        btnThoat.setBorderPainted(false);
        btnDung.setOpaque(false);
        btnDung.setContentAreaFilled(false);
        btnDung.setBorderPainted(false);
        btnSai.setOpaque(false);
        btnSai.setContentAreaFilled(false);
        btnSai.setBorderPainted(false);
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnReplay.setOpaque(false);
        btnReplay.setContentAreaFilled(false);
        btnReplay.setBorderPainted(false);
        
        btnThachDau.setOpaque(false);
        btnThachDau.setContentAreaFilled(false);
        btnThachDau.setBorderPainted(false);
        btnDong.setOpaque(false);
        btnDong.setContentAreaFilled(false);
        btnDong.setBorderPainted(false);
        btnBac.setOpaque(false);
        btnBac.setContentAreaFilled(false);
        btnBac.setBorderPainted(false);
        btnVang.setOpaque(false);
        btnVang.setContentAreaFilled(false);
        btnVang.setBorderPainted(false);
        btnBachKim.setOpaque(false);
        btnBachKim.setContentAreaFilled(false);
        btnBachKim.setBorderPainted(false);
        btnKimCuong.setOpaque(false);
        btnKimCuong.setContentAreaFilled(false);
        btnKimCuong.setBorderPainted(false);
        btnCaoThu.setOpaque(false);
        btnCaoThu.setContentAreaFilled(false);
        btnCaoThu.setBorderPainted(false);
        
    }

    private void docFileVaoTable() {
        String line = "";
        while (true) {
            line = f.docFile1();
            if (line == null) {
                break;
            }
            String[] str = line.split(",");
            model.addRow(str);
        }
    }
    private void ChonPanel(JPanel panel) {
        Layer.removeAll();
        Layer.add(panel);
        Layer.repaint();
        Layer.revalidate();
    }
    private void Dung() {
        if (!gameOver) {
            if ((a + b == cong) || (a - b == tru) || (a * b == nhan) || ((double) a / b == chia)) {
//                File file = new File(Song_True);
//                PlaySound.playSound(file);
                diemBestMoi++;
                switch (mucdo) {
                    case 1:
                        load(9);
                        thoiGian = 200;
                        break;
                    case 2:
                        load(99);
                        thoiGian = 200;
                        break;
                    case 3:
                        load(999);
                        thoiGian = 200;
                        break;
                    case 4:
                        load(9999);
                        thoiGian = 200;
                        break;
                    case 5:
                        load(99999);
                        thoiGian = 200;
                        break;
                    case 6:
                        load(999999);
                        thoiGian = 200;
                        break;
                    case 7:
                        load(9999999);
                        thoiGian = 200;
                        break;
                }
                t.setDelay(--delay);
                t.start();

            } else {
//                File file = new File(Song_False);
//                PlaySound.playSound(file);
                ChonPanel(panelThua);
                gameOver = true;
                t.stop();
                String s = diemBestMoi + "," + formatter.format(new Date());
                File_Manager.GhiFile(s);
            }
            if (diemBestMoi > diemBestCu) {
                diemBestCu = diemBestMoi;
                lblDiemNew.setText(diemBestMoi + "");
                lblDiemBest.setText(diemBestMoi + "");

            } else {
                lblDiemNew.setText(diemBestMoi + "");
                lblDiemBest.setText(diemBestCu + "");
            }
        }
    }
    private void Sai() {
        if (!gameOver) {

            if ((a + b != cong) || (a - b != tru) || (a * b != nhan) || ((double) a / b != chia)) {
//                File file = new File(Song_True);
//                PlaySound.playSound(file);
                diemBestMoi++;
                switch (mucdo) {
                    case 1:
                        load(9);
                        thoiGian = 200;
                        break;
                    case 2:
                        load(99);
                        thoiGian = 200;
                        break;
                    case 3:
                        load(999);
                        thoiGian = 200;
                        break;
                    case 4:
                        load(9999);
                        thoiGian = 200;
                        break;
                    case 5:
                        load(99999);
                        thoiGian = 200;
                        break;
                    case 6:
                        load(999999);
                        thoiGian = 200;
                        break;
                    case 7:
                        load(9999999);
                        thoiGian = 200;
                        break;
                }
                t.setDelay(--delay);
                t.start();
            } else {
//                File file = new File(Song_False);
//                PlaySound.playSound(file);
                ChonPanel(panelThua);
                //thoiGian = 100;
                gameOver = true;
                t.stop();
                String s = diemBestMoi + "," + formatter.format(new Date());
                File_Manager.GhiFile(s);
            }
            if (diemBestMoi > diemBestCu) {
                diemBestCu = diemBestMoi;
                lblDiemNew.setText(diemBestMoi + "");
                lblDiemBest.setText(diemBestMoi + "");

            } else {
                lblDiemNew.setText(diemBestMoi + "");
                lblDiemBest.setText(diemBestCu + "");
            }
        }
    }

    private void load(int range) {
        Random rd = new Random();
        a = rd.nextInt(range) + 1;//
        b = rd.nextInt(range) + 1;//
        lblDiemSo_panelChoi.setText("" + diemBestMoi);
        checkCauHoi = rd.nextInt(2) + 1;
        System.out.println(checkCauHoi);
        if (checkCauHoi % 2 == 0) {
            cong = a + b;
            tru = a - b;
            nhan = a * b;
            chia = (double) a / b;

        } else {
            cong = rd.nextInt(range + range) + 1;//
            tru = (int)(Math.random()*range) - range;
            nhan = (int)(Math.random()*range) * range;
            chia = rd.nextInt(range + range) + rd.nextDouble();//

        }
        String arr[] = {"" + BigDecimal.valueOf(a).toPlainString().replace(".0", "") + " + " + BigDecimal.valueOf(b).toPlainString().replace(".0", "") + " = " + BigDecimal.valueOf(cong).toPlainString().replace(".0", "") + "",
            "" + BigDecimal.valueOf(a).toPlainString().replace(".0", "") + " - " + BigDecimal.valueOf(b).toPlainString().replace(".0", "") + " = " + BigDecimal.valueOf(tru).toPlainString().replace(".0", "") + "",
            "" + BigDecimal.valueOf(a).toPlainString().replace(".0", "") + " * " + BigDecimal.valueOf(b).toPlainString().replace(".0", "") + " = " + BigDecimal.valueOf(nhan).toPlainString().replace(".0", "")+ "",
            "" + BigDecimal.valueOf(a).toPlainString() .replace(".0", "")+ " / " + BigDecimal.valueOf(b).toPlainString().replace(".0", "") + " = " + BigDecimal.valueOf(chia).toPlainString() + ""};
        int random = (int) (Math.random() * 4);
        lblCauHoi.setText(arr[random]);

    }

    class loadtime implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (thoiGian > 0) {
                thoiGian--;
                progressBar.setValue(thoiGian);

            } else {
                ChonPanel(panelThua);
                t.stop();
                String s = diemBestMoi + "," + formatter.format(new Date());
                File_Manager.GhiFile(s);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Layer = new javax.swing.JLayeredPane();
        panelManHinhChinh = new JPanel();
        lblAnhManHinhChinh = new JLabel();
        btnChoi = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnDiemCao = new javax.swing.JButton();
        btnHuongdan = new javax.swing.JButton();
        lblBanQuyen = new JLabel();
        panelThua = new JPanel();
        btnHome = new javax.swing.JButton();
        btnReplay = new javax.swing.JButton();
        panelKetQua = new JPanel();
        lblDiemNew = new JLabel();
        lblDiemBest = new JLabel();
        lblDiemNew1 = new JLabel();
        lblDiemNew2 = new JLabel();
        jLabel2 = new JLabel();
        panelHuongDan = new JPanel();
        lblAnhManHinhChinh1 = new JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAHuongdan = new javax.swing.JTextArea();
        lblTroVe_huongdan = new JLabel();
        panelChoi = new JPanel();
        progressBar = new javax.swing.JProgressBar();
        lblDiemSo_panelChoi = new JLabel();
        lblDiem_panelChoi = new JLabel();
        btnDung = new javax.swing.JButton();
        btnSai = new javax.swing.JButton();
        panelCauhoi = new JPanel();
        lblCauHoi = new JLabel();
        panelDiemCao = new JPanel();
        scrollDiemCao = new javax.swing.JScrollPane();
        tableDiemCao = new javax.swing.JTable();
        lblTroVe_diemcao = new JLabel();
        panelMucDo = new JPanel();
        btnDong = new javax.swing.JButton();
        btnKimCuong = new javax.swing.JButton();
        btnCaoThu = new javax.swing.JButton();
        btnThachDau = new javax.swing.JButton();
        btnBachKim = new javax.swing.JButton();
        btnVang = new javax.swing.JButton();
        btnBac = new javax.swing.JButton();
        jLabel1 = new JLabel();
        jLabel3 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tay Nhanh Hơn Não");

        Layer.setLayout(new java.awt.CardLayout());

        panelManHinhChinh.setBackground(new Color(132, 55, 125));
        panelManHinhChinh.setPreferredSize(new java.awt.Dimension(462, 600));

        lblAnhManHinhChinh.setFont(new Font("Tahoma", 1, 36)); // NOI18N
        lblAnhManHinhChinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhManHinhChinh.setText("Tay nhanh hơn não");

        btnChoi.setIcon(new ImageIcon("btnChoi.png")); // NOI18N
        btnChoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChoiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChoiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnChoiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnChoiMouseReleased(evt);
            }
        });
        btnChoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChoiActionPerformed(evt);
            }
        });

        btnThoat.setIcon(new ImageIcon("btnThoat.png")); // NOI18N
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThoatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThoatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThoatMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnThoatMouseReleased(evt);
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnDiemCao.setIcon(new ImageIcon("btnDiemcao.png")); // NOI18N
        btnDiemCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDiemCaoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDiemCaoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDiemCaoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDiemCaoMouseReleased(evt);
            }
        });
        btnDiemCao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDiemCaoActionPerformed(evt);
            }
        });

        btnHuongdan.setIcon(new ImageIcon("btnHuongdan.png")); // NOI18N
        btnHuongdan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHuongdanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHuongdanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHuongdanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHuongdanMouseReleased(evt);
            }
        });
        btnHuongdan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnHuongdanActionPerformed(evt);
            }
        });

        lblBanQuyen.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        lblBanQuyen.setForeground(new Color(255, 66, 0));
        lblBanQuyen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBanQuyen.setText("HienXT - ©Copyright - Version 1.0");
        lblBanQuyen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBanQuyenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelManHinhChinhLayout = new javax.swing.GroupLayout(panelManHinhChinh);
        panelManHinhChinh.setLayout(panelManHinhChinhLayout);
        panelManHinhChinhLayout.setHorizontalGroup(
            panelManHinhChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhManHinhChinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1219, Short.MAX_VALUE)
            .addGroup(panelManHinhChinhLayout.createSequentialGroup()
                .addGap(523, 523, 523)
                .addGroup(panelManHinhChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuongdan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDiemCao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManHinhChinhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBanQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelManHinhChinhLayout.setVerticalGroup(
            panelManHinhChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManHinhChinhLayout.createSequentialGroup()
                .addComponent(lblAnhManHinhChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnDiemCao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnHuongdan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(lblBanQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Layer.add(panelManHinhChinh, "card2");

        panelThua.setBackground(new Color(132, 55, 125));

        btnHome.setIcon(new ImageIcon("btnHome.png")); // NOI18N
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHomeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHomeMouseReleased(evt);
            }
        });
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnReplay.setIcon(new ImageIcon("btnReplay.png")); // NOI18N
        btnReplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReplayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReplayMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReplayMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnReplayMouseReleased(evt);
            }
        });
        btnReplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnReplayActionPerformed(evt);
            }
        });

        lblDiemNew.setFont(new Font("Tahoma", 1, 48)); // NOI18N

        lblDiemBest.setFont(new Font("Tahoma", 1, 48)); // NOI18N

        lblDiemNew1.setFont(new Font("Tahoma", 1, 36)); // NOI18N
        lblDiemNew1.setText("Điểm cao :");

        lblDiemNew2.setFont(new Font("Tahoma", 1, 36)); // NOI18N
        lblDiemNew2.setText("Điểm mới :");

        jLabel2.setFont(new Font("Tahoma", 1, 100)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Game Over");

        javax.swing.GroupLayout panelKetQuaLayout = new javax.swing.GroupLayout(panelKetQua);
        panelKetQua.setLayout(panelKetQuaLayout);
        panelKetQuaLayout.setHorizontalGroup(
            panelKetQuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKetQuaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKetQuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDiemNew1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDiemNew2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 800, Short.MAX_VALUE)
                .addGroup(panelKetQuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiemNew, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemBest, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelKetQuaLayout.setVerticalGroup(
            panelKetQuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKetQuaLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelKetQuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiemNew2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemNew, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(panelKetQuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDiemBest, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemNew1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

        javax.swing.GroupLayout panelThuaLayout = new javax.swing.GroupLayout(panelThua);
        panelThua.setLayout(panelThuaLayout);
        panelThuaLayout.setHorizontalGroup(
            panelThuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThuaLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 893, Short.MAX_VALUE)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(panelThuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelThuaLayout.setVerticalGroup(
            panelThuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelThuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(123, 123, 123))
        );

        Layer.add(panelThua, "card4");

        panelHuongDan.setBackground(new Color(132, 55, 125));

        lblAnhManHinhChinh1.setFont(new Font("Tahoma", 1, 36)); // NOI18N
        lblAnhManHinhChinh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhManHinhChinh1.setText("Tay nhanh hơn não");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 66, 0)));

        textAHuongdan.setEditable(false);
        textAHuongdan.setBackground(new Color(132, 55, 125));
        textAHuongdan.setColumns(20);
        textAHuongdan.setFont(new Font("Monospaced", 1, 14)); // NOI18N
        textAHuongdan.setRows(5);
        textAHuongdan.setText("* Tại giao diện màn hình chính : \n  có 4 nút : Chơi,Điểm cao,Hướng dẫn,Thoát. \n- Khi bấm vào nút Chơi : xuất hiện giao diện Chơi \n  bao gồm : \n  + Thanh load thời gian.\n  + Điểm số\n  + Câu hỏi\n  + 2 nút trả lời đúng , sai.\n  Câu hỏi xuất hiện ngẫu nhiên với các phép toán : \n  cộng trừ nhân chia 2 chữ số,\n  Nếu người chơi trả lời đúng,điểm số sẽ tăng lên 1 \n  đồng thời thời gian sẽ nhanh hơn trước,\n  người chơi trả lời sai game sẽ dừng lại \n  và tổng kết điểm số.\n- Khi bấm vào nút Điểm cao : \n  xuất hiện giao diện Điểm cao ,\n  liệt kê những lần chơi trước đó của bạn\n  và số điểm được sắp xếp theo thứ tự giảm dần.\n- khi bấm vào nút Hướng dẫn :\n  Giao diện Hướng dẫn như hình đó.\n- Khi bấm vào nít Thoát :\n  sẽ hiển thị 1 dialog xác nhận thoát ?.");
        textAHuongdan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hướng dẫn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 3, 36), new Color(255, 66, 0))); // NOI18N
        jScrollPane1.setViewportView(textAHuongdan);

        lblTroVe_huongdan.setIcon(new ImageIcon("icons8-back-40.png")); // NOI18N
        lblTroVe_huongdan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTroVe_huongdanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelHuongDanLayout = new javax.swing.GroupLayout(panelHuongDan);
        panelHuongDan.setLayout(panelHuongDanLayout);
        panelHuongDanLayout.setHorizontalGroup(
            panelHuongDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhManHinhChinh1, javax.swing.GroupLayout.DEFAULT_SIZE, 1219, Short.MAX_VALUE)
            .addGroup(panelHuongDanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHuongDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHuongDanLayout.createSequentialGroup()
                        .addComponent(lblTroVe_huongdan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panelHuongDanLayout.setVerticalGroup(
            panelHuongDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHuongDanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTroVe_huongdan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnhManHinhChinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
        );

        Layer.add(panelHuongDan, "card5");

        panelChoi.setBackground(new Color(132, 55, 125));

        progressBar.setMaximum(500);

        lblDiemSo_panelChoi.setFont(new Font("Tahoma", 1, 36)); // NOI18N
        lblDiemSo_panelChoi.setForeground(new Color(255, 66, 0));

        lblDiem_panelChoi.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        lblDiem_panelChoi.setForeground(new Color(255, 66, 0));
        lblDiem_panelChoi.setText("Điểm :");

        btnDung.setIcon(new ImageIcon("btnDUNG_defalt.png")); // NOI18N
        btnDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDungMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDungMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDungMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDungMouseReleased(evt);
            }
        });
        btnDung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDungActionPerformed(evt);
            }
        });

        btnSai.setIcon(new ImageIcon("btnSai_defalt.png")); // NOI18N
        btnSai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSaiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSaiMouseReleased(evt);
            }
        });
        btnSai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSaiActionPerformed(evt);
            }
        });

        lblCauHoi.setFont(new Font("Tahoma", 0, 50)); // NOI18N
        lblCauHoi.setForeground(new Color(44, 0, 30));
        lblCauHoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelCauhoiLayout = new javax.swing.GroupLayout(panelCauhoi);
        panelCauhoi.setLayout(panelCauhoiLayout);
        panelCauhoiLayout.setHorizontalGroup(
            panelCauhoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelCauhoiLayout.setVerticalGroup(
            panelCauhoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelChoiLayout = new javax.swing.GroupLayout(panelChoi);
        panelChoi.setLayout(panelChoiLayout);
        panelChoiLayout.setHorizontalGroup(
            panelChoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChoiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChoiLayout.createSequentialGroup()
                        .addGap(0, 427, Short.MAX_VALUE)
                        .addGroup(panelChoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChoiLayout.createSequentialGroup()
                                .addComponent(lblDiem_panelChoi)
                                .addGap(18, 18, 18)
                                .addComponent(lblDiemSo_panelChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChoiLayout.createSequentialGroup()
                                .addComponent(btnDung, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSai, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(368, 368, 368))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChoiLayout.createSequentialGroup()
                        .addComponent(panelCauhoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelChoiLayout.setVerticalGroup(
            panelChoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChoiLayout.createSequentialGroup()
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelChoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDiemSo_panelChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiem_panelChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(panelCauhoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelChoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDung, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSai, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        Layer.add(panelChoi, "card3");

        panelDiemCao.setBackground(new Color(132, 55, 125));

        scrollDiemCao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Điểm Cao", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 3, 36), new Color(255, 66, 0))); // NOI18N

        tableDiemCao.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 66, 0)));
        tableDiemCao.setForeground(new Color(255, 66, 0));
        tableDiemCao.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Điểm", "Ngày Chơi"
            }
        ));
        tableDiemCao.getTableHeader().setResizingAllowed(false);
        tableDiemCao.getTableHeader().setReorderingAllowed(false);
        scrollDiemCao.setViewportView(tableDiemCao);

        lblTroVe_diemcao.setIcon(new ImageIcon("icons8-back-40.png")); // NOI18N
        lblTroVe_diemcao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTroVe_diemcaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelDiemCaoLayout = new javax.swing.GroupLayout(panelDiemCao);
        panelDiemCao.setLayout(panelDiemCaoLayout);
        panelDiemCaoLayout.setHorizontalGroup(
            panelDiemCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDiemCao, javax.swing.GroupLayout.DEFAULT_SIZE, 1219, Short.MAX_VALUE)
            .addGroup(panelDiemCaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTroVe_diemcao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDiemCaoLayout.setVerticalGroup(
            panelDiemCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDiemCaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTroVe_diemcao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDiemCao, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE))
        );

        Layer.add(panelDiemCao, "card6");

        panelMucDo.setBackground(new Color(132, 55, 125));

        btnDong.setIcon(new ImageIcon("bronze.png")); // NOI18N
        btnDong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        btnKimCuong.setIcon(new ImageIcon("diamond.png")); // NOI18N
        btnKimCuong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnKimCuongActionPerformed(evt);
            }
        });

        btnCaoThu.setIcon(new ImageIcon("master.png")); // NOI18N
        btnCaoThu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCaoThuActionPerformed(evt);
            }
        });

        btnThachDau.setIcon(new ImageIcon("challenger.png")); // NOI18N
        btnThachDau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThachDauActionPerformed(evt);
            }
        });

        btnBachKim.setIcon(new ImageIcon("platinum.png")); // NOI18N
        btnBachKim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBachKimActionPerformed(evt);
            }
        });

        btnVang.setIcon(new ImageIcon("gold.png")); // NOI18N
        btnVang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVangActionPerformed(evt);
            }
        });

        btnBac.setIcon(new ImageIcon("silver.png")); // NOI18N
        btnBac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBacActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new ImageIcon("icons8-back-40.png")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Mức độ");

        javax.swing.GroupLayout panelMucDoLayout = new javax.swing.GroupLayout(panelMucDo);
        panelMucDo.setLayout(panelMucDoLayout);
        panelMucDoLayout.setHorizontalGroup(
            panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMucDoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMucDoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBachKim, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(262, 262, 262)
                .addGroup(panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThachDau, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnBac, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKimCuong, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                .addGroup(panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCaoThu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        panelMucDoLayout.setVerticalGroup(
            panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMucDoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBac, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(panelMucDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCaoThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBachKim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKimCuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addComponent(btnThachDau, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Layer.add(panelMucDo, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Layer)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Layer)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReplayActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnReplayActionPerformed
        switch (mucdo) {
            case 1:
                t.stop();
                diemBestMoi = 0;
                delay = 50;
                btnDongActionPerformed(evt);
                break;
            case 2:
                t.stop();
                diemBestMoi = 0;
                delay = 50;
                btnBacActionPerformed(evt);
                break;
            case 3:
                t.stop();
                diemBestMoi = 0;
                delay = 50;
                btnVangActionPerformed(evt);
                break;
            case 4:
                t.stop();
                diemBestMoi = 0;
                delay = 50;
                btnBacActionPerformed(evt);
                break;
            case 5:
                t.stop();
                diemBestMoi = 0;
                delay = 50;
                btnKimCuongActionPerformed(evt);
                break;

            case 6:
                t.stop();
                diemBestMoi = 0;
                delay = 50;
                btnCaoThuActionPerformed(evt);
                break;
            case 7:
                t.stop();
                diemBestMoi = 0;
                delay = 50;
                btnThachDauActionPerformed(evt);
                break;
        }
    }//GEN-LAST:event_btnReplayActionPerformed

    private void btnChoiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnChoiActionPerformed
        ChonPanel(panelMucDo);
    }//GEN-LAST:event_btnChoiActionPerformed

    private void btnThoatActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát", "Thoát", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDiemCaoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnDiemCaoActionPerformed
        ChonPanel(panelDiemCao);
        docFileVaoTable();
    }//GEN-LAST:event_btnDiemCaoActionPerformed

    private void btnHuongdanActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnHuongdanActionPerformed
        ChonPanel(panelHuongDan);
    }//GEN-LAST:event_btnHuongdanActionPerformed

    private void btnDungActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnDungActionPerformed
        Dung();
    }//GEN-LAST:event_btnDungActionPerformed

    private void btnSaiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnSaiActionPerformed
        Sai();
    }//GEN-LAST:event_btnSaiActionPerformed

    private void btnDungMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDungMouseEntered
        ImageIcon II = new ImageIcon("btnDUNG_hover.png");
        btnDung.setIcon(II);
    }//GEN-LAST:event_btnDungMouseEntered

    private void btnDungMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDungMouseExited
        ImageIcon II = new ImageIcon("btnDUNG_defalt.png");
        btnDung.setIcon(II);
    }//GEN-LAST:event_btnDungMouseExited

    private void btnDungMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDungMousePressed
        ImageIcon II = new ImageIcon("DungNot3D.png");
        btnDung.setIcon(II);
    }//GEN-LAST:event_btnDungMousePressed

    private void btnDungMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDungMouseReleased
        ImageIcon II = new ImageIcon("btnDUNG_hover.png");
        btnDung.setIcon(II);
    }//GEN-LAST:event_btnDungMouseReleased

    private void btnSaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaiMouseEntered
        ImageIcon II = new ImageIcon("btnSai_hover.png");
        btnSai.setIcon(II);
    }//GEN-LAST:event_btnSaiMouseEntered

    private void btnSaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaiMouseExited
        ImageIcon II = new ImageIcon("btnSai_defalt.png");
        btnSai.setIcon(II);
    }//GEN-LAST:event_btnSaiMouseExited

    private void btnSaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaiMousePressed
        ImageIcon II = new ImageIcon("btnSaiNot3D.png");
        btnSai.setIcon(II);
    }//GEN-LAST:event_btnSaiMousePressed

    private void btnSaiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaiMouseReleased
        ImageIcon II = new ImageIcon("btnSai_hover.png");
        btnSai.setIcon(II);
    }//GEN-LAST:event_btnSaiMouseReleased

    private void btnChoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChoiMouseEntered
        ImageIcon II = new ImageIcon("btnChoi_hover.png");
        btnChoi.setIcon(II);
    }//GEN-LAST:event_btnChoiMouseEntered

    private void btnChoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChoiMouseExited
        ImageIcon II = new ImageIcon("btnChoi.png");
        btnChoi.setIcon(II);
    }//GEN-LAST:event_btnChoiMouseExited

    private void btnChoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChoiMousePressed
        ImageIcon II = new ImageIcon("btnChoiNot3D.png");
        btnChoi.setIcon(II);
    }//GEN-LAST:event_btnChoiMousePressed

    private void btnChoiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChoiMouseReleased
        ImageIcon II = new ImageIcon("btnChoi.png");
        btnChoi.setIcon(II);
    }//GEN-LAST:event_btnChoiMouseReleased

    private void btnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseEntered
        ImageIcon II = new ImageIcon("btnThoat_hover.png");
        btnThoat.setIcon(II);
    }//GEN-LAST:event_btnThoatMouseEntered

    private void btnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseExited
        ImageIcon II = new ImageIcon("btnThoat.png");
        btnThoat.setIcon(II);
    }//GEN-LAST:event_btnThoatMouseExited

    private void btnThoatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMousePressed
        ImageIcon II = new ImageIcon("btnThoatNot3D.png");
        btnThoat.setIcon(II);
    }//GEN-LAST:event_btnThoatMousePressed

    private void btnThoatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseReleased
        ImageIcon II = new ImageIcon("btnThoat.png");
        btnThoat.setIcon(II);
    }//GEN-LAST:event_btnThoatMouseReleased

    private void btnHuongdanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuongdanMouseReleased
        ImageIcon II = new ImageIcon("btnHuongdan.png");
        btnHuongdan.setIcon(II);
    }//GEN-LAST:event_btnHuongdanMouseReleased

    private void btnHuongdanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuongdanMousePressed
        ImageIcon II = new ImageIcon("btnHuongdanNot3D.png");
        btnHuongdan.setIcon(II);
    }//GEN-LAST:event_btnHuongdanMousePressed

    private void btnHuongdanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuongdanMouseExited
        ImageIcon II = new ImageIcon("btnHuongdan.png");
        btnHuongdan.setIcon(II);
    }//GEN-LAST:event_btnHuongdanMouseExited

    private void btnHuongdanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuongdanMouseEntered
        ImageIcon II = new ImageIcon("btnHuongdan_hover.png");
        btnHuongdan.setIcon(II);
    }//GEN-LAST:event_btnHuongdanMouseEntered

    private void btnDiemCaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiemCaoMouseEntered
        ImageIcon II = new ImageIcon("btnDiemcao_hover.png");
        btnDiemCao.setIcon(II);
    }//GEN-LAST:event_btnDiemCaoMouseEntered

    private void btnDiemCaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiemCaoMouseExited
        ImageIcon II = new ImageIcon("btnDiemcao.png");
        btnDiemCao.setIcon(II);
    }//GEN-LAST:event_btnDiemCaoMouseExited

    private void btnDiemCaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiemCaoMousePressed
        ImageIcon II = new ImageIcon("btnDiemcaoNot3D.png");
        btnDiemCao.setIcon(II);
    }//GEN-LAST:event_btnDiemCaoMousePressed

    private void btnDiemCaoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiemCaoMouseReleased
        ImageIcon II = new ImageIcon("btnDiemcao.png");
        btnDiemCao.setIcon(II);
    }//GEN-LAST:event_btnDiemCaoMouseReleased

    private void btnReplayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReplayMouseEntered
        ImageIcon II = new ImageIcon("btnReplay_hover.png");
        btnReplay.setIcon(II);
    }//GEN-LAST:event_btnReplayMouseEntered

    private void btnReplayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReplayMouseExited
        ImageIcon II = new ImageIcon("btnReplay.png");
        btnReplay.setIcon(II);
    }//GEN-LAST:event_btnReplayMouseExited

    private void btnReplayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReplayMousePressed
        ImageIcon II = new ImageIcon("btnReplayNot3D.png");
        btnReplay.setIcon(II);
    }//GEN-LAST:event_btnReplayMousePressed

    private void btnReplayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReplayMouseReleased
        ImageIcon II = new ImageIcon("btnReplay.png");
        btnReplay.setIcon(II);
    }//GEN-LAST:event_btnReplayMouseReleased

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        ImageIcon II = new ImageIcon("btnHome_hover.png");
        btnHome.setIcon(II);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        ImageIcon II = new ImageIcon("btnHome.png");
        btnHome.setIcon(II);
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMousePressed
        ImageIcon II = new ImageIcon("btnHomeNot3D.png");
        btnHome.setIcon(II);
    }//GEN-LAST:event_btnHomeMousePressed

    private void btnHomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseReleased
        ImageIcon II = new ImageIcon("btnHome.png");
        btnHome.setIcon(II);
    }//GEN-LAST:event_btnHomeMouseReleased

    private void btnHomeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        ChonPanel(panelManHinhChinh);
        //thoiGian = 100;
        diemBestMoi = 0;
        delay = 50;
        t.stop();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void lblBanQuyenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBanQuyenMouseClicked
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.facebook.com/HienDepTrai3006"));
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblBanQuyenMouseClicked

    private void lblTroVe_huongdanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTroVe_huongdanMouseClicked
        ChonPanel(panelManHinhChinh);
    }//GEN-LAST:event_lblTroVe_huongdanMouseClicked

    private void lblTroVe_diemcaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTroVe_diemcaoMouseClicked
        ChonPanel(panelManHinhChinh);
    }//GEN-LAST:event_lblTroVe_diemcaoMouseClicked

    private void btnDongActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        mucdo = 1;
        ChonPanel(panelChoi);
        gameOver = false;
        lblDiemSo_panelChoi.setText("" + diemBestMoi);
        thoiGian = 200;
        progressBar.setMaximum(thoiGian);
        System.out.println(thoiGian);
        load(9);
        t = new Timer(delay, new loadtime());
        t.start();
        lblDiemNew.setText(diemBestMoi + "");
        lblDiemBest.setText(diemBestCu + "");
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnKimCuongActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnKimCuongActionPerformed
        mucdo = 5;
        ChonPanel(panelChoi);
        gameOver = false;
        lblDiemSo_panelChoi.setText("" + diemBestMoi);
        thoiGian = 200;
        progressBar.setMaximum(thoiGian);
        load(99999);
        t = new Timer(delay, new loadtime());
        t.start();
        lblDiemNew.setText(diemBestMoi + "");
        lblDiemBest.setText(diemBestCu + "");
    }//GEN-LAST:event_btnKimCuongActionPerformed

    private void btnCaoThuActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnCaoThuActionPerformed
        mucdo = 6;
        ChonPanel(panelChoi);
        gameOver = false;
        lblDiemSo_panelChoi.setText("" + diemBestMoi);
        thoiGian = 200;
        progressBar.setMaximum(thoiGian);
        load(999999);
        t = new Timer(delay, new loadtime());
        t.start();
        lblDiemNew.setText(diemBestMoi + "");
        lblDiemBest.setText(diemBestCu + "");
    }//GEN-LAST:event_btnCaoThuActionPerformed

    private void btnThachDauActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnThachDauActionPerformed
        mucdo = 7;
        ChonPanel(panelChoi);
        gameOver = false;
        lblDiemSo_panelChoi.setText("" + diemBestMoi);
        thoiGian = 200;
        progressBar.setMaximum(thoiGian);
        load(9999999);
        t = new Timer(delay, new loadtime());
        t.start();
        lblDiemNew.setText(diemBestMoi + "");
        lblDiemBest.setText(diemBestCu + "");
    }//GEN-LAST:event_btnThachDauActionPerformed

    private void btnBachKimActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnBachKimActionPerformed
        mucdo = 4;
        ChonPanel(panelChoi);
        gameOver = false;
        lblDiemSo_panelChoi.setText("" + diemBestMoi);
        thoiGian = 200;
        progressBar.setMaximum(thoiGian);
        load(9999);
        t = new Timer(delay, new loadtime());
        t.start();
        lblDiemNew.setText(diemBestMoi + "");
        lblDiemBest.setText(diemBestCu + "");
    }//GEN-LAST:event_btnBachKimActionPerformed

    private void btnVangActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnVangActionPerformed
        mucdo = 3;
        ChonPanel(panelChoi);
        gameOver = false;
        lblDiemSo_panelChoi.setText("" + diemBestMoi);
        thoiGian = 200;
        progressBar.setMaximum(thoiGian);
        load(999);
        t = new Timer(delay, new loadtime());
        t.start();
        lblDiemNew.setText(diemBestMoi + "");
        lblDiemBest.setText(diemBestCu + "");
    }//GEN-LAST:event_btnVangActionPerformed

    private void btnBacActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnBacActionPerformed
        mucdo = 2;
        ChonPanel(panelChoi);
        gameOver = false;
        lblDiemSo_panelChoi.setText("" + diemBestMoi);
        thoiGian = 200;
        progressBar.setMaximum(thoiGian);
        load(99);
        t = new Timer(delay, new loadtime());
        t.start();
        lblDiemNew.setText(diemBestMoi + "");
        lblDiemBest.setText(diemBestCu + "");
    }//GEN-LAST:event_btnBacActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        ChonPanel(panelManHinhChinh);
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormMain().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane Layer;
    private javax.swing.JButton btnBac;
    private javax.swing.JButton btnBachKim;
    private javax.swing.JButton btnCaoThu;
    private javax.swing.JButton btnChoi;
    private javax.swing.JButton btnDiemCao;
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnDung;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHuongdan;
    private javax.swing.JButton btnKimCuong;
    private javax.swing.JButton btnReplay;
    private javax.swing.JButton btnSai;
    private javax.swing.JButton btnThachDau;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnVang;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private JLabel lblAnhManHinhChinh;
    private JLabel lblAnhManHinhChinh1;
    private JLabel lblBanQuyen;
    private JLabel lblCauHoi;
    private JLabel lblDiemBest;
    private JLabel lblDiemNew;
    private JLabel lblDiemNew1;
    private JLabel lblDiemNew2;
    private JLabel lblDiemSo_panelChoi;
    private JLabel lblDiem_panelChoi;
    private JLabel lblTroVe_diemcao;
    private JLabel lblTroVe_huongdan;
    private JPanel panelCauhoi;
    private JPanel panelChoi;
    private JPanel panelDiemCao;
    private JPanel panelHuongDan;
    private JPanel panelKetQua;
    private JPanel panelManHinhChinh;
    private JPanel panelMucDo;
    private JPanel panelThua;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JScrollPane scrollDiemCao;
    private javax.swing.JTable tableDiemCao;
    private javax.swing.JTextArea textAHuongdan;
    // End of variables declaration//GEN-END:variables
}
