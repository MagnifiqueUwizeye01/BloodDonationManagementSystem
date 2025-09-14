/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import javax.swing.*;
import javax.swing.table.*;
import dao.DonorDao;
import model.Donor;
import model.User;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.DonorService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.DonorService;


// 🧾 iText PDF library
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

// 📁 File handling
import java.io.File;
import java.io.FileOutputStream;

// 🖱️ GUI Components
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author User
 */
public class DonorManagementForm extends javax.swing.JPanel {
    private User currentUser; 
    private DefaultTableModel tableModel; 
    private final String[] BLOOD_TYPES = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
   


    public DonorManagementForm() {
        initComponents();
        setupForm();
    }

    public DonorManagementForm(User user, DonorService donorService){
       this.currentUser = user;
        initComponents();
        this.setPreferredSize(new Dimension(1000, 700));
        setupForm();
        
        

    
}


    private void setupForm() {
        initializeTable();
        setupValidations();
        loadDonorData();
    }
    
    private void initializeTable() {
    
    tableModel = new DefaultTableModel(
        new Object[]{"ID", "Name", "Blood Type", "Phone", "Email"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    
    
    tblDonors.setModel(tableModel);
    
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    tblDonors.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    tblDonors.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    
  
    tblDonors.setRowHeight(30);
    
    
    tblDonors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    
    tblDonors.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tblDonors.getSelectedRow();
            btnUpdate.setEnabled(selectedRow != -1);
            if (selectedRow != -1) {
                
                txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                cmbBloodType.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
                txtPhone.setText(tableModel.getValueAt(selectedRow, 3).toString());
                txtEmail.setText(tableModel.getValueAt(selectedRow, 4).toString());
            }
        }
    });
}
    
    private void setupValidations() {
    
    txtName.setInputVerifier(new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            if (!text.isEmpty() && !text.matches("[a-zA-Z\\s]{3,}")) {
                showValidationError("Name must contain only letters and be at least 3 characters", "Invalid Name");
                return false;
            }
            return true;
        }
    });

    
    txtPhone.setInputVerifier(new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            if (!text.isEmpty() && !text.matches("\\d{10}")) {
                showValidationError("Phone must be exactly 10 digits", "Invalid Phone");
                return false;
            }
            return true;
        }
    });

    
    txtEmail.setInputVerifier(new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            if (!text.isEmpty() && !text.matches(".+@.+\\..+")) {
                showValidationError("Please enter a valid email address", "Invalid Email");
                return false;
            }
            return true;
        }
    });

    
    cmbBloodType.addActionListener(e -> {
        if (cmbBloodType.getSelectedIndex() == -1) {
            showValidationError("Please select a blood type", "Blood Type Required");
        }
    });
}

private void showValidationError(String message, String title) {
    JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cmbBloodType = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        scrlDonors = new javax.swing.JScrollPane();
        tblDonors = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        exportPdfButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1700, 800));

        jPanel1.setBackground(new java.awt.Color(255, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add/Update Donor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(850, 600));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Blood Type:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Phone:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Email:");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        cmbBloodType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        cmbBloodType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBloodTypeActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(76, 175, 80));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add Donor");
        btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(33, 150, 243));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Donor");
        btnUpdate.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setEnabled(false);
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(102, 102, 102));
        searchBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Search Donor");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                            .addComponent(jLabel3))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtName)
                                .addComponent(txtEmail)
                                .addComponent(cmbBloodType, 0, 241, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(btnAdd)
                        .addGap(122, 122, 122)
                        .addComponent(btnUpdate)
                        .addGap(154, 154, 154)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(322, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnAdd)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(321, 321, 321))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Donor List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDonors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "BloodType", "Phone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlDonors.setViewportView(tblDonors);

        btnDelete.setBackground(new java.awt.Color(244, 67, 54));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete selected Donor");
        btnDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        exportPdfButton.setBackground(new java.awt.Color(153, 204, 255));
        exportPdfButton.setText("Export PDF");
        exportPdfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPdfButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrlDonors, javax.swing.GroupLayout.DEFAULT_SIZE, 1622, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportPdfButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(exportPdfButton)
                .addGap(18, 18, 18)
                .addComponent(scrlDonors, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(220, 20, 60));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("DONOR MANAGEMENT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1658, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(407, 407, 407)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void cmbBloodTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBloodTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBloodTypeActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!validateAllFields()) return;

try {
    // Get donor info from form
    String name = txtName.getText().trim();
    String phone = txtPhone.getText().trim();
    String email = txtEmail.getText().trim();
    String bloodType = cmbBloodType.getSelectedItem().toString();

    // Connect to RMI
    Registry registry = LocateRegistry.getRegistry("localhost", 5000);
    DonorService donorService = (DonorService) registry.lookup("donor");

    // Check if donor already exists
    if (donorService.donorExists(name, phone)) {
        JOptionPane.showMessageDialog(this,
            "This donor already exists (same name and phone number).",
            "Duplicate Donor",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Create Donor object
    Donor newDonor = new Donor(0, name, bloodType, phone, email);

    // Save donor remotely
    int result = donorService.saveDonor(newDonor);

    if (result > 0) {
        JOptionPane.showMessageDialog(this, "✅ Donor added successfully!");
        loadDonorData();  // Refresh table
        clearFields();    // Clear form
    } else {
        JOptionPane.showMessageDialog(this, "❌ Failed to add donor", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (Exception ex) {
    showError("❌ Error adding donor via RMI: " + ex.getMessage());
    ex.printStackTrace();
}

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       int selectedRow = tblDonors.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a donor to update",
            "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (!validateAllFields()) return;

    try {
        int donorId = (int) tableModel.getValueAt(selectedRow, 0);

        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        String bloodType = cmbBloodType.getSelectedItem().toString();

        Donor updatedDonor = new Donor(donorId, name, bloodType, phone, email);

        Registry registry = LocateRegistry.getRegistry("localhost", 5000);
        DonorService donorService = (DonorService) registry.lookup("donor");

        boolean updated = donorService.updateDonor(updatedDonor);

        if (updated) {
            JOptionPane.showMessageDialog(this, "✅ Donor updated successfully!");
            loadDonorData();
            clearFields();
            tblDonors.clearSelection();
            btnUpdate.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Update failed",
                "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        showError("Error updating donor via RMI: " + ex.getMessage());
        ex.printStackTrace();
    }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // 1. Always clear previous filter first
        tblDonors.setRowSorter(null);

        // 2. Get search input
        String searchTerm = JOptionPane.showInputDialog(
            this,
            "Enter donor name to search:",
            "Search Donors",
            JOptionPane.PLAIN_MESSAGE
        );

        // 3. If user cancelled or entered nothing, show all donors
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            loadDonorData();
            return;
        }

        // 4. Apply new filter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        tblDonors.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm.trim(), 1)); // Case-insensitive

        // 5. If no results, show message but keep filter
        if (tblDonors.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "No donors found matching: " + searchTerm,
                "No Results",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
int selectedRow = tblDonors.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a donor to delete",
            "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int donorId = (int) tableModel.getValueAt(selectedRow, 0);
    String donorName = (String) tableModel.getValueAt(selectedRow, 1);

    try {
        DonorService donorService = (DonorService) java.rmi.Naming.lookup("rmi://localhost:5000/donor");

        if (donorService.hasDonations(donorId)) {
            int choice = JOptionPane.showConfirmDialog(this,
                "⚠️ Warning: " + donorName + " has donation records.\n" +
                "Deleting will keep donations but remove donor information.\n\n" +
                "Proceed with deletion?",
                "Donor Has Donations",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete donor: " + donorName + "?",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = donorService.deleteDonor(donorId);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "✅ Donor deleted successfully!");
                loadDonorData(); // Refresh table
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to delete donor",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (Exception ex) {
        showError("Error deleting donor via RMI: " + ex.getMessage());
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void exportPdfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPdfButtonActionPerformed
        try {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF");
        fileChooser.setSelectedFile(new File("donor_report.pdf")); // default filename

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection != JFileChooser.APPROVE_OPTION) return;

        File pdfFile = fileChooser.getSelectedFile();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        document.open();

        document.add(new Paragraph("Donor Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph("Generated on: " + new java.util.Date()));
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(tblDonors.getColumnCount());
        table.setWidthPercentage(100);

        // Add table headers
        for (int i = 0; i < tblDonors.getColumnCount(); i++) {
            table.addCell(new PdfPCell(new Phrase(tblDonors.getColumnName(i))));
        }

        // Add table rows
        for (int row = 0; row < tblDonors.getRowCount(); row++) {
            for (int col = 0; col < tblDonors.getColumnCount(); col++) {
                Object value = tblDonors.getValueAt(row, col);
                table.addCell(value != null ? value.toString() : "");
            }
        }

        document.add(table);
        document.close();

        JOptionPane.showMessageDialog(this, "✅ Donor report exported successfully!\nSaved to: " + pdfFile.getAbsolutePath());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "❌ Failed to export donor report: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_exportPdfButtonActionPerformed
private boolean validateAllFields() {
    // Check empty fields
    if (txtName.getText().trim().isEmpty()) {
        showValidationError("Name is required", "Missing Field");
        txtName.requestFocus();
        return false;
    }
    
    if (txtPhone.getText().trim().isEmpty()) {
        showValidationError("Phone is required", "Missing Field");
        txtPhone.requestFocus();
        return false;
    }
    
    if (txtEmail.getText().trim().isEmpty()) {
        showValidationError("Email is required", "Missing Field");
        txtEmail.requestFocus();
        return false;
    }
    
    // Name validation
    if (!txtName.getText().matches("[a-zA-Z\\s]{3,}")) {
        showValidationError("Name must contain only letters and be at least 3 characters", "Invalid Name");
        txtName.requestFocus();
        return false;
    }

    // Phone validation
    if (!txtPhone.getText().matches("\\d{10}")) {
        showValidationError("Phone must be exactly 10 digits", "Invalid Phone");
        txtPhone.requestFocus();
        return false;
    }

    // Email validation
    if (!txtEmail.getText().matches(".+@.+\\..+")) {
        showValidationError("Please enter a valid email address", "Invalid Email");
        txtEmail.requestFocus();
        return false;
    }

    // Blood type validation
    if (cmbBloodType.getSelectedIndex() == -1) {
        showValidationError("Please select a blood type", "Blood Type Required");
        cmbBloodType.requestFocus();
        return false;
    }

    return true;
}
    
    private void clearFields() {
    txtName.setText("");
    txtPhone.setText("");
    txtEmail.setText("");
    cmbBloodType.setSelectedIndex(0); // Select first item or none
}
    
    private void loadDonorData() {
    tableModel.setRowCount(0); // Clear existing data

    try {
        // Connect to the RMI Registry
        Registry registry = LocateRegistry.getRegistry("localhost", 5000);
        DonorService donorService = (DonorService) registry.lookup("donor");

        // Call remote method
        List<Donor> donors = donorService.getAllDonors();

        // Fill table
        for (Donor donor : donors) {
            tableModel.addRow(new Object[]{
                donor.getDonorId(),
                donor.getNames(),
                donor.getBloodType(),
                donor.getPhone(),
                donor.getEmail()
            });
        }

    } catch (Exception ex) {
        showError("❌ Failed to load donors via RMI: " + ex.getMessage());
        ex.printStackTrace();
    }
}
    
    

    
    private void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
}
    
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
            java.util.logging.Logger.getLogger(DonorManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DonorManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DonorManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DonorManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DonorManagementForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbBloodType;
    private javax.swing.JButton exportPdfButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane scrlDonors;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable tblDonors;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
