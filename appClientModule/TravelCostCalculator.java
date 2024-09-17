import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TravelCostCalculator extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField tfDistance;
    private JTextField tfFuelPrice;
    private JTextField tfFuelConsumption;
    private JTextField tfToll;
    private JLabel lblResult;

    public TravelCostCalculator() {
        setTitle("Calculadora de Custo de Viagem");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("KM - Quilometragem: "));
        tfDistance = new JTextField();
        add(tfDistance);

        add(new JLabel("Valor do Combustível (€/Litro): "));
        tfFuelPrice = new JTextField();
        add(tfFuelPrice);

        add(new JLabel("Consumo (litros/100km): "));
        tfFuelConsumption = new JTextField();
        add(tfFuelConsumption);

        add(new JLabel("Custo Pedágio (€): "));
        tfToll = new JTextField();
        add(tfToll);

        JButton btnCalculate = new JButton("Calcular");
        add(btnCalculate);

        lblResult = new JLabel("Custo total da viagem: €0.00");
        add(lblResult);

        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCost();
            }
        });
    }

    private void calculateCost() {
        try {
            double distance = Double.parseDouble(tfDistance.getText());
            double fuelPrice = Double.parseDouble(tfFuelPrice.getText());
            double fuelConsumption = Double.parseDouble(tfFuelConsumption.getText());
            double toll = Double.parseDouble(tfToll.getText());

            double totalFuelNeeded = (distance / 100) * 2 * fuelConsumption; // ida e volta
            double totalFuelCost = totalFuelNeeded * fuelPrice;
            double totalCost = totalFuelCost + toll;

            lblResult.setText(String.format("<html><body><b>Custo total da viagem: €%.2f</b></body></html>", totalCost));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TravelCostCalculator().setVisible(true);
            }
        });
    }
}
