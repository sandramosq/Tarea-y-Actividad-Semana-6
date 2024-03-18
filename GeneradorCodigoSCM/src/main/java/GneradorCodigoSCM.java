import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GneradorCodigoSCM extends javax.swing.JFrame {

  
    public GneradorCodigoSCM() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();
        btnGenerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 51, 255));

        txtResultado.setColumns(20);
        txtResultado.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        txtCodigo.setColumns(20);
        txtCodigo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtCodigo.setRows(5);
        jScrollPane2.setViewportView(txtCodigo);

        btnGenerar.setBackground(new java.awt.Color(204, 204, 255));
        btnGenerar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(102, 0, 153));
        btnGenerar.setText("Generar Codigo");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addComponent(btnGenerar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(540, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(btnGenerar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(24, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
         generarCodigoIntermedio();
    }//GEN-LAST:event_btnGenerarActionPerformed

      private void generarCodigoIntermedio() {
        String sourceCode = txtCodigo.getText();
        IntermediateCodeGenerator generator = new IntermediateCodeGenerator();
        List<String> intermediateCode = generator.generateIntermediateCode(sourceCode);

        // Limpiar el texto del resultado antes de agregar el nuevo código intermedio
        txtResultado.setText("");

        // Agregar el código intermedio al JTextArea
        for (String code : intermediateCode) {
            txtResultado.append(code + "\n");
        }
    }

   class IntermediateCodeGenerator {
    public List<String> generateIntermediateCode(String sourceCode) {
        List<String> intermediateCode = new ArrayList<>();
        String[] lines = sourceCode.split("\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                // Si la línea está en blanco, ignorarla
                continue;
            }

            String[] tokens = line.split("\\s+");
            String opcode = tokens[0]; // El primer token es el opcode
            String operand1 = (tokens.length > 1) ? tokens[1] : "";
            String operand2 = (tokens.length > 2) ? tokens[2] : "";

            // Generar código intermedio basado en el opcode y los operandos
            if (opcode.equals("var")) {
                intermediateCode.add("DECLARE " + operand1);
            } else if (opcode.equals("print")) {
                intermediateCode.add("PRINT " + operand1);
            } else if (tokens.length > 2 && tokens[1].equals("=")) {
                // Si la línea contiene un operador '=' en la segunda posición, es una asignación
                intermediateCode.add("ASSIGN " + operand1 + " " + operand2);
            } else {
                // Opcode no reconocido
                intermediateCode.add("ERROR: Opcode no reconocido en la línea: " + line);
            }
        }

        return intermediateCode;
    }
}

    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GneradorCodigoSCM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtCodigo;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
