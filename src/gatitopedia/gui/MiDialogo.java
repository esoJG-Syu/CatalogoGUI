/*
 * Paradigmas de Programación
 * José Enrique González Sánchez
 * Grupo 412
 * 08-10s-2024
 * Reto 3
 */

package gatitopedia.gui;

import javax.swing.*;

import gatitopedia.dominio.Gato;
import gatitopedia.utilerias.MiFocusTraversalPolicy;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * MiDialogo representa un diálogo personalizado para manejar un catálogo de
 * gatos. Extiende JDialog e implementa ActionListener e ItemListener para
 * manejar eventos.
 */
public class MiDialogo extends JDialog implements ItemListener {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal ventanaPrincipal;

	private JMenu menuOperaciones;
	private JMenuItem operacionNuevo;
	private JMenuItem operacionModificar;
	private JMenuItem operacionGuardar;
	private JMenuItem operacionEliminar;
	private JMenuItem operacionCancelar;
	private JMenuBar barraMenu;

	private JButton bNuevo;
	private JButton bModificar;
	private JButton bGuardar;
	private JButton bEliminar;
	private JButton bCancelar;

	private JLabel entidadLabel;
	private JComboBox<Gato> entidad;

	private JLabel edadLabel;
	private JTextField edadGato;

	private JLabel pesoLabel;
	private JTextField pesoGato;

	private JLabel precioLabel;
	private JTextField precioAdopcion;

	private JLabel nombreLabel;
	private JTextField nombreGato;

	private JLabel idLabel;
	private JTextField identificadorGato;

	private JLabel fechaNLabel;
	private JTextField fechaNacimiento;

	private JLabel sexoLabel;
	private JRadioButton macho;
	private JRadioButton hembra;

	private JLabel esterilizadoLabel;
	private JRadioButton gatoEsterilizadoSI;
	private JRadioButton gatoEsterilizadoNO;

	private JLabel personalidadLabel;
	private JComboBox<String> personalidad;
	private JButton bAñadirPersonalidad;
	private JButton bQuitarPersonalidad;

	private JLabel habitosLabel;
	private JCheckBox habito1;
	private JCheckBox habito2;
	private JCheckBox habito3;
	private JCheckBox habito4;

	private JLabel ColorLabel;
	private JComboBox<String> color;
	private JButton bAñadirColor;
	private JButton bQuitarColor;

	// Componentes para la imagen
	private JLabel etiquetaImagen;
	private JTextField espacioImagen;
	private JButton botonAñadirImagen;

	// Acciones
	private Action nuevo;
	private Action modificar;
	private Action guardar;
	private Action eliminar;
	private Action cancelar;
	private Action añadirP;
	private Action quitarP;
	private Action añadirC;
	private Action quitarC;
	private Action seleccionar;

	/**
	 * Constructor de MiDialogo.
	 */
	public MiDialogo(JFrame principal) {
		super(principal, "Cátalogo de gatos", true);
		ventanaPrincipal = (VentanaPrincipal) principal;
		crearAcciones();
		inicializarComponentes();
		establecerPoliticaFoco();
		inicializar();
		lanzarDialogo();
	}

	private void inicializarComponentes() {
		//////////////// Menús //////////////////////
		menuOperaciones = new JMenu("Operaciones");
		menuOperaciones.setIcon(new ImageIcon(getClass().getResource("/gatitopedia/imagenes/operaciones.png")));
		menuOperaciones.setMnemonic(KeyEvent.VK_O);

		operacionNuevo = new JMenuItem(nuevo);
		operacionModificar = new JMenuItem(modificar);
		operacionGuardar = new JMenuItem(guardar);
		operacionEliminar = new JMenuItem(eliminar);
		operacionCancelar = new JMenuItem(cancelar);

		/////////////// Botones //////////////////
		bNuevo = new JButton(nuevo);
		bNuevo.getActionMap().put("BotonNuevo", nuevo);
		bNuevo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) nuevo.getValue(Action.ACCELERATOR_KEY),
				"BotonNuevo");

		bModificar = new JButton(modificar);
		bModificar.getActionMap().put("BotonModificar", modificar);
		bModificar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) modificar.getValue(Action.ACCELERATOR_KEY), "BotonModifiar");

		bGuardar = new JButton(guardar);
		bGuardar.getActionMap().put("BotonGuardar", guardar);
		bGuardar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) guardar.getValue(Action.ACCELERATOR_KEY), "BotonGuardar");

		bEliminar = new JButton(eliminar);
		bEliminar.getActionMap().put("BotonEliminar", eliminar);
		bEliminar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) eliminar.getValue(Action.ACCELERATOR_KEY), "BotonEliminar");

		bCancelar = new JButton(cancelar);
		bCancelar.getActionMap().put("BotonCancelar", cancelar);
		bCancelar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) cancelar.getValue(Action.ACCELERATOR_KEY), "BotonCancelar");

		//////////////// Crear la barra y añadir los elementos ////////////////////////
		barraMenu = new JMenuBar();

		menuOperaciones.add(operacionNuevo);
		menuOperaciones.add(operacionModificar);
		menuOperaciones.add(operacionGuardar);
		menuOperaciones.add(operacionEliminar);
		menuOperaciones.add(operacionCancelar);

		barraMenu.add(menuOperaciones);
		this.setJMenuBar(barraMenu);

		//////////// Componentes /////////////////////////

		// I - Entidad
		entidadLabel = new JLabel("Entidades:");
		entidadLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		entidad = new JComboBox<Gato>();
		entidad.setPreferredSize(new Dimension(260, 20));
		entidad.setToolTipText("Seleccione una entidad");
		entidad.addItemListener(this);

		// II - Edad
		edadLabel = new JLabel("Edad:");
		edadGato = new JTextField(20);
		edadLabel.setLabelFor(edadGato);
		edadLabel.setDisplayedMnemonic(KeyEvent.VK_E);
		edadGato.setToolTipText("Edad del animal");

		// III - Peso
		pesoLabel = new JLabel("Peso:");
		pesoGato = new JTextField(20);
		pesoLabel.setLabelFor(pesoGato);
		pesoLabel.setDisplayedMnemonic(KeyEvent.VK_S);
		pesoGato.setToolTipText("Peso del animal");

		// IV - Precio
		precioLabel = new JLabel("Precio:");
		precioAdopcion = new JTextField(20);
		precioLabel.setLabelFor(precioAdopcion);
		precioLabel.setDisplayedMnemonic(KeyEvent.VK_C);
		precioAdopcion.setToolTipText("Costo de adopción del animal");

		// V - Nombre
		nombreLabel = new JLabel("Nombre:");
		nombreGato = new JTextField(20);
		nombreLabel.setLabelFor(nombreGato);
		nombreLabel.setDisplayedMnemonic(KeyEvent.VK_M);
		nombreGato.setToolTipText("Nombre que tiene el animal");

		// VI - Indentificador
		idLabel = new JLabel("Identificador:");
		identificadorGato = new JTextField(20);
		idLabel.setLabelFor(identificadorGato);
		idLabel.setDisplayedMnemonic(KeyEvent.VK_D);
		identificadorGato.setToolTipText("Identificador asignado al animal");

		// VII - Fecha Nacimiento
		fechaNLabel = new JLabel("Fecha de nacimiento:");
		fechaNacimiento = new JTextField(20);
		fechaNLabel.setLabelFor(fechaNacimiento);
		fechaNLabel.setDisplayedMnemonic(KeyEvent.VK_F);
		fechaNacimiento.setToolTipText("Fecha en la que nacio el animal");

		// VIII - Género
		sexoLabel = new JLabel("Género:");
		sexoLabel.setToolTipText("Seleccione una opción");
		macho = new JRadioButton("Macho");
		hembra = new JRadioButton("Hembra");
		sexoLabel.setDisplayedMnemonic(KeyEvent.VK_G);
		sexoLabel.setLabelFor(macho);
		macho.setToolTipText("Seleccione si es un macho");
		macho.setSelected(true);
		hembra.setToolTipText("Seleccione si es una hembra");

		ButtonGroup grupoGenero = new ButtonGroup();
		grupoGenero.add(macho);
		grupoGenero.add(hembra);

		// IX - Esterilizado
		esterilizadoLabel = new JLabel("Esterilizado:");
		esterilizadoLabel.setToolTipText("Seleccione una opción");
		gatoEsterilizadoSI = new JRadioButton("Si");
		gatoEsterilizadoNO = new JRadioButton("No");
		esterilizadoLabel.setDisplayedMnemonic(KeyEvent.VK_Z);
		esterilizadoLabel.setLabelFor(gatoEsterilizadoSI);
		gatoEsterilizadoSI.setToolTipText("Seleccione si su gato está esterilizado");
		gatoEsterilizadoSI.setSelected(true);
		gatoEsterilizadoNO.setToolTipText("Seleccione si su gato no está esterilizado");

		ButtonGroup esterilizado = new ButtonGroup();
		esterilizado.add(gatoEsterilizadoSI);
		esterilizado.add(gatoEsterilizadoNO);

		// X - Personalidad
		personalidadLabel = new JLabel("Personalidad:");
		personalidad = new JComboBox<>(new String[] { "Amistoso", "Agresivo", "Tímido", "Juguetón" });
		personalidad.setPreferredSize(new Dimension(220, 20));
		personalidadLabel.setDisplayedMnemonic(KeyEvent.VK_P);
		personalidadLabel.setLabelFor(personalidad);
		personalidad.setToolTipText("Seleccione o añada la personalidad del gato");
		personalidad.setEditable(true);

		bAñadirPersonalidad = new JButton(añadirP);
		bAñadirPersonalidad.setPreferredSize(new Dimension(110, 40));
		bAñadirPersonalidad.getActionMap().put("BotonAñadirP", añadirP);
		bAñadirPersonalidad.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) añadirP.getValue(Action.ACCELERATOR_KEY), "BotonAñadirP");

		bQuitarPersonalidad = new JButton(quitarP);
		bQuitarPersonalidad.setPreferredSize(new Dimension(110, 40));
		bQuitarPersonalidad.getActionMap().put("BotonQuitarP", quitarP);
		bQuitarPersonalidad.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) quitarP.getValue(Action.ACCELERATOR_KEY), "BotonQuitarP");

		// XI - Habitos
		habitosLabel = new JLabel("Habitos:");
		habito1 = new JCheckBox("Usa caja de arena");
		habito2 = new JCheckBox("Usa rascador");
		habito3 = new JCheckBox("Camina con correa");
		habito4 = new JCheckBox("Obedece órdenes");
		habitosLabel.setDisplayedMnemonic(KeyEvent.VK_H);

		// XII - Color
		ColorLabel = new JLabel("Color:");
		color = new JComboBox<>(new String[] { "Blanco", "Negro", "Gris", "Naranja", "Marrón" });
		color.setPreferredSize(new Dimension(220, 20));
		ColorLabel.setDisplayedMnemonic(KeyEvent.VK_C);
		ColorLabel.setLabelFor(color);
		color.setToolTipText("Seleccione o añada el color del gato");
		color.setEditable(true);

		bAñadirColor = new JButton(añadirC);
		bAñadirColor.setPreferredSize(new Dimension(110, 40));
		bAñadirColor.getActionMap().put("BotonAñadirC", añadirC);
		bAñadirColor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) añadirC.getValue(Action.ACCELERATOR_KEY), "BotonAñadirC");

		bQuitarColor = new JButton(quitarC);
		bQuitarColor.setPreferredSize(new Dimension(110, 40));
		bQuitarColor.getActionMap().put("BotonQuitarC", quitarC);
		bQuitarColor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) quitarC.getValue(Action.ACCELERATOR_KEY), "BotonQuitarC");

		// XII - Imagen
		etiquetaImagen = new JLabel("Imagen:");
		espacioImagen = new JTextField();
		espacioImagen.setPreferredSize(new Dimension(200, 57));
		espacioImagen.setEditable(false);

		botonAñadirImagen = new JButton(seleccionar);
		etiquetaImagen.setDisplayedMnemonic(KeyEvent.VK_M);
		etiquetaImagen.setLabelFor(espacioImagen);
		botonAñadirImagen.setMnemonic(KeyEvent.VK_L);

		/////////////// Paneles ////////////
		JPanel panelN = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelN.add(entidadLabel);
		panelN.add(entidad);

		JPanel panelEs = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel panelAux = new JPanel(new GridLayout(9, 1));
		panelAux.add(bNuevo);
		panelAux.add(new JPanel());
		panelAux.add(bModificar);
		panelAux.add(new JPanel());
		panelAux.add(bGuardar);
		panelAux.add(new JPanel());
		panelAux.add(bEliminar);
		panelAux.add(new JPanel());
		panelAux.add(bCancelar);
		panelEs.add(panelAux);

		JPanel panelCentral1 = new JPanel(new GridLayout(9, 2));

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(edadLabel);
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2.add(edadGato);
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3.add(pesoLabel);
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel4.add(pesoGato);
		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel5.add(precioLabel);
		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel6.add(precioAdopcion);
		JPanel panel7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel7.add(nombreLabel);
		JPanel panel8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel8.add(nombreGato);
		JPanel panel9 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel9.add(idLabel);
		JPanel panel10 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel10.add(identificadorGato);
		JPanel panel11 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel11.add(fechaNLabel);
		JPanel panel12 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel12.add(fechaNacimiento);
		JPanel panel13 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel13.add(sexoLabel);
		JPanel panel14 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel14.add(macho);
		panel14.add(hembra);

		JPanel panel15 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel15.add(habitosLabel);

		JPanel panel16 = new JPanel(new GridLayout(2, 1));
		JPanel panelAux1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAux1.add(habito1);
		JPanel panelAux2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAux2.add(habito2);

		panel16.add(panelAux1);
		panel16.add(panelAux2);

		JPanel panel17 = new JPanel();
		panel17.add(new JPanel());

		JPanel panel18 = new JPanel(new GridLayout(2, 1));
		JPanel panelAux3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAux3.add(habito3);
		JPanel panelAux4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAux4.add(habito4);

		panel18.add(panelAux3);
		panel18.add(panelAux4);

		JPanel panelCentral2 = new JPanel(new GridLayout(7, 2));

		JPanel panelC1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC1.add(esterilizadoLabel);
		JPanel panelC2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC2.add(gatoEsterilizadoSI);
		panelC2.add(gatoEsterilizadoNO);
		JPanel panelC3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC3.add(personalidadLabel);
		JPanel panelC4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC4.add(personalidad);
		JPanel panelC5 = new JPanel();
		panelC5.add(new JPanel());
		JPanel panelC6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC6.add(bAñadirPersonalidad);
		panelC6.add(bQuitarPersonalidad);
		JPanel panelC7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC7.add(ColorLabel);
		JPanel panelC8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC8.add(color);
		JPanel panelC9 = new JPanel();
		panelC9.add(new JPanel());
		JPanel panelC10 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC10.add(bAñadirColor);
		panelC10.add(bQuitarColor);

		JPanel panelC11 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC11.add(etiquetaImagen);
		JPanel panelC12 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC12.add(espacioImagen);
		JPanel panelC13 = new JPanel();
		panelC13.add(new JPanel());
		JPanel panelC14 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelC14.add(botonAñadirImagen);

		panelCentral1.add(panel1);
		panelCentral1.add(panel2);
		panelCentral1.add(panel3);
		panelCentral1.add(panel4);
		panelCentral1.add(panel5);
		panelCentral1.add(panel6);
		panelCentral1.add(panel7);
		panelCentral1.add(panel8);
		panelCentral1.add(panel9);
		panelCentral1.add(panel10);
		panelCentral1.add(panel11);
		panelCentral1.add(panel12);
		panelCentral1.add(panel13);
		panelCentral1.add(panel14);
		panelCentral1.add(panel15);
		panelCentral1.add(panel16);
		panelCentral1.add(panel17);
		panelCentral1.add(panel18);

		panelCentral2.add(panelC1);
		panelCentral2.add(panelC2);
		panelCentral2.add(panelC3);
		panelCentral2.add(panelC4);
		panelCentral2.add(panelC5);
		panelCentral2.add(panelC6);
		panelCentral2.add(panelC7);
		panelCentral2.add(panelC8);
		panelCentral2.add(panelC9);
		panelCentral2.add(panelC10);
		panelCentral2.add(panelC11);
		panelCentral2.add(panelC12);
		panelCentral2.add(panelC13);
		panelCentral2.add(panelC14);

		JPanel panelCentral = new JPanel(new GridLayout(1, 2));
		panelCentral.add(panelCentral1);
		panelCentral.add(panelCentral2);

		// PANEL PRINCIPAL
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.add(panelN, BorderLayout.NORTH);
		panelPrincipal.add(panelEs, BorderLayout.EAST);
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		this.add(panelPrincipal);
	}

	private void lanzarDialogo() {
		this.setSize(1280, 600);
		this.setLocationRelativeTo(ventanaPrincipal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/gatitopedia/imagenes/icono.png")));

		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setVisible(true);
	}

	private void crearAcciones() {

		// Acción nuevo
		nuevo = new AbstractAction("Nuevo", new ImageIcon(getClass().getResource("/gatitopedia/imagenes/nuevo.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionNuevo();
			}
		};
		nuevo.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		nuevo.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		nuevo.putValue(Action.SHORT_DESCRIPTION, "Nuevo Registro");

		// Acción Modificar
		modificar = new AbstractAction("Modificar",
				new ImageIcon(getClass().getResource("/gatitopedia/imagenes/modificar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionModificar();
			}
		};

		modificar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		modificar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		modificar.putValue(Action.SHORT_DESCRIPTION, "Modificar Registro");

		// Acción Guardar
		guardar = new AbstractAction("Guardar",
				new ImageIcon(getClass().getResource("/gatitopedia/imagenes/guardar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionGuardar();
			}
		};
		guardar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		guardar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
		guardar.putValue(Action.SHORT_DESCRIPTION, "Guardar Registro");

		// Acción Eliminar
		eliminar = new AbstractAction("Eliminar",
				new ImageIcon(getClass().getResource("/Gatitopedia/imagenes/eliminar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionEliminar();
			}
		};
		eliminar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		eliminar.putValue(Action.SHORT_DESCRIPTION, "Eliminar Registro");

		// Acción Cancelar
		cancelar = new AbstractAction("Cancelar",
				new ImageIcon(getClass().getResource("/gatitopedia/imagenes/cancelar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionCancelar();
			}
		};
		cancelar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
		cancelar.putValue(Action.SHORT_DESCRIPTION, "Cancelar un nuevo registro");

		// Acción seleccioanar imagen
		seleccionar = new AbstractAction("Seleccionar",
				new ImageIcon(getClass().getResource("/gatitopedia/imagenes/select.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionAñadirImagen();
			}
		};
		seleccionar.putValue(Action.SHORT_DESCRIPTION, "Selecionar una imagen para subir");

		// Acción Añadir personalidad
		añadirP = new AbstractAction("Añadir",
				new ImageIcon(getClass().getResource("/gatitopedia/imagenes/añadir.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionAñadirPersonalidad();
			}
		};
		añadirP.putValue(Action.SHORT_DESCRIPTION, "Añade una personalidad más a la lista");

		// Acción Quitar personalidad
		quitarP = new AbstractAction("Quitar",
				new ImageIcon(getClass().getResource("/gatitopedia/imagenes/quitar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionQuitarPersonalidad();
			}
		};
		quitarP.putValue(Action.SHORT_DESCRIPTION, "Quita una personalidad de la lista");

		// Acción añadir color
		añadirC = new AbstractAction("Añadir",
				new ImageIcon(getClass().getResource("/gatitopedia/imagenes/añadir.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionAñadirColor();
			}
		};
		añadirC.putValue(Action.SHORT_DESCRIPTION, "Añade un color más a la lista");

		// Acción Quitar color
		quitarC = new AbstractAction("Quitar",
				new ImageIcon(getClass().getResource("/gatitopedia/imagenes/quitar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				accionQuitarColor();
			}
		};
		quitarC.putValue(Action.SHORT_DESCRIPTION, "Quita un color de la lista");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// Manejo de eventos de cambio de estado en los JComboBox
		
		Gato gato = (Gato) entidad.getSelectedItem();
		
		edadGato.setText(String.valueOf(gato.getEdadGato()));
		pesoGato.setText(String.valueOf(gato.getPesoGato()));
		precioAdopcion.setText(String.valueOf(gato.getPrecioAdopcion()));
		nombreGato.setText(String.valueOf(gato.getNombreGato()));
		identificadorGato.setText(String.valueOf(gato.getIdentificadorGato()));
		
		// Restaurar posición de los radios
		
		String sexo = gato.getSexoGato();
		if(sexo.equals(macho.getText())) {
			macho.setSelected(true);
		} else {
			hembra.setSelected(true);
		}
		
		boolean castrado = gato.getGatoEsterilizado();
		if(castrado) {
			gatoEsterilizadoSI.setSelected(true);
		} else {
			gatoEsterilizadoNO.setSelected(true);
		}
	}

	// Métodos para manejar las acciones de los botones del menú
	private void accionNuevo() {
		limpiarCampos();
		habilitarCampos(true);
		
		
		bGuardar.setEnabled(true);
	    operacionGuardar.setEnabled(true);
	    bCancelar.setEnabled(true);
	    operacionCancelar.setEnabled(true);
	    
	    bNuevo.setEnabled(false);
	    operacionNuevo.setEnabled(false);
	    bModificar.setEnabled(false);
	    operacionModificar.setEnabled(false);
	    bEliminar.setEnabled(false);
	    operacionEliminar.setEnabled(false);
	    entidad.setEnabled(false);
	}

	private void accionModificar() {
		JOptionPane.showMessageDialog(this, "Acción 'Modificar' ejecutada.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void accionGuardar() {
		// Objeto de la entidad gato
		Gato gato = new Gato();
		
		//
		gato.setEdadGato(edadGato.getText());
		gato.setPesoGato(pesoGato.getText());
		gato.setPrecioAdopcion(precioAdopcion.getText());
		gato.setNombreGato(nombreGato.getText());
		gato.setIdentificadorGato(identificadorGato.getText());
		
		// Radios Macho, Hembra, esterilizado
		if(macho.isSelected()) {
			gato.setSexoGato(macho.getText());
		} else if(hembra.isSelected()) {
			gato.setSexoGato(hembra.getText());
		}
		
		if(gatoEsterilizadoSI.isSelected()) {
			gato.setGatoEsterilizado(true);
		} else if(gatoEsterilizadoNO.isSelected()) {
			gato.setGatoEsterilizado(false);
		}
		
		
		JOptionPane.showMessageDialog(this, "Entidad Guardada", "Información",
				JOptionPane.INFORMATION_MESSAGE);
		
		entidad.addItem(gato);
		
		accionCancelar();
		
	}

	private void accionEliminar() {
		JOptionPane.showMessageDialog(this, "Acción 'Eliminar' ejecutada.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void accionCancelar() {
		limpiarCampos();
		habilitarCampos(false);
		
		bGuardar.setEnabled(false);
	    operacionGuardar.setEnabled(false);
	    bCancelar.setEnabled(false);
	    operacionCancelar.setEnabled(false);
	    
	    bNuevo.setEnabled(true);
	    operacionNuevo.setEnabled(true);
		
		verificarLista();
	}

	// Métodos para manejar las acciones de los botones auxiliares
	private void accionAñadirPersonalidad() {
		JOptionPane.showMessageDialog(this, "Acción 'Añadir Personalidad' ejecutada.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void accionQuitarPersonalidad() {
		JOptionPane.showMessageDialog(this, "Acción 'Quitar Personalidad' ejecutada.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void accionAñadirColor() {
		JOptionPane.showMessageDialog(this, "Acción 'Añadir Color' ejecutada.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void accionQuitarColor() {
		JOptionPane.showMessageDialog(this, "Acción 'Quitar Color' ejecutada.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void accionAñadirImagen() {
		JOptionPane.showMessageDialog(this, "Acción 'Añadir Imagen' ejecutada.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Establece la política de enfoque personalizada para el diálogo.
	 */
	private void establecerPoliticaFoco() {
		Vector<Component> componentes = new Vector<Component>();
		componentes.add(bNuevo);
		componentes.add(bModificar);
		componentes.add(bGuardar);
		componentes.add(bEliminar);
		componentes.add(bCancelar);
		componentes.add(entidad);
		componentes.add(edadGato);
		componentes.add(pesoGato);
		componentes.add(precioAdopcion);
		componentes.add(nombreGato);
		componentes.add(identificadorGato);
		componentes.add(fechaNacimiento);
		componentes.add(macho);
		componentes.add(gatoEsterilizadoSI);
		componentes.add(personalidad);
		componentes.add(bAñadirPersonalidad);
		componentes.add(bQuitarPersonalidad);
		componentes.add(habito1);
		componentes.add(habito2);
		componentes.add(habito3);
		componentes.add(habito4);
		componentes.add(color);
		componentes.add(bAñadirColor);
		componentes.add(bQuitarColor);
		componentes.add(botonAñadirImagen);
		MiFocusTraversalPolicy politicaFoco = new MiFocusTraversalPolicy(componentes);
		this.setFocusTraversalPolicy(politicaFoco);
	}

	private void limpiarCampos() {
	    // Limpiar los campos de texto
	    nombreGato.setText("");
	    identificadorGato.setText("");
	    fechaNacimiento.setText("");
	    pesoGato.setText("");
	    precioAdopcion.setText("");
	    edadGato.setText("");
	    // Restablecer los botones de opción a su valor predeterminado
	    macho.setSelected(true);
	    gatoEsterilizadoSI.setSelected(true);
	    // Desmarcar las casillas de verificación
	    habito1.setSelected(false);
	    habito2.setSelected(false);
	    habito3.setSelected(false);
	    habito4.setSelected(false);
	}
	
	private void habilitarCampos(boolean habilitar) {
	    // Habilitar o deshabilitar los campos
		
	    nombreGato.setEnabled(habilitar);
	    identificadorGato.setEnabled(habilitar);
	    fechaNacimiento.setEnabled(habilitar);
	    pesoGato.setEnabled(habilitar);
	    precioAdopcion.setEnabled(habilitar);
	    edadGato.setEnabled(habilitar);
	    personalidad.setEnabled(habilitar);
	    color.setEnabled(habilitar);
	    macho.setEnabled(habilitar);
	    hembra.setEnabled(habilitar);
	    gatoEsterilizadoSI.setEnabled(habilitar);
	    gatoEsterilizadoNO.setEnabled(habilitar);
	    habito1.setEnabled(habilitar);
	    habito2.setEnabled(habilitar);
	    habito3.setEnabled(habilitar);
	    habito4.setEnabled(habilitar);
	    bAñadirPersonalidad.setEnabled(habilitar);
	    bQuitarPersonalidad.setEnabled(habilitar);
	    bAñadirColor.setEnabled(habilitar);
	    bQuitarColor.setEnabled(habilitar);
	    botonAñadirImagen.setEnabled(habilitar);
	}

	private void verificarLista() {
	    if (entidad.getItemCount() > 0) {
	        entidad.setEnabled(true);
	        bModificar.setEnabled(true);
	        operacionModificar.setEnabled(true);
	        bEliminar.setEnabled(true);
	        operacionEliminar.setEnabled(true);
	    } else {
	        entidad.setEnabled(false);
	        bModificar.setEnabled(false);
	        operacionModificar.setEnabled(false);
	        bEliminar.setEnabled(false);
	        operacionEliminar.setEnabled(false);
	    }
	}

	
	void inicializar() {
		entidad.setEnabled(false);
	    nombreGato.setEnabled(false);
	    identificadorGato.setEnabled(false);
	    fechaNacimiento.setEnabled(false);
	    pesoGato.setEnabled(false);
	    precioAdopcion.setEnabled(false);
	    edadGato.setEnabled(false);
	    personalidad.setEnabled(false);
	    color.setEnabled(false);
	    macho.setEnabled(false);
	    hembra.setEnabled(false);
	    gatoEsterilizadoSI.setEnabled(false);
	    gatoEsterilizadoNO.setEnabled(false);
	    habito1.setEnabled(false);
	    habito2.setEnabled(false);
	    habito3.setEnabled(false);
	    habito4.setEnabled(false);
	    bAñadirPersonalidad.setEnabled(false);
	    bQuitarPersonalidad.setEnabled(false);
	    bAñadirColor.setEnabled(false);
	    bQuitarColor.setEnabled(false);
	    botonAñadirImagen.setEnabled(false);
	    
	    // Botones
	    bNuevo.setEnabled(true);
	    operacionNuevo.setEnabled(true);
	    
	    bGuardar.setEnabled(false);
	    operacionGuardar.setEnabled(false);
	    bCancelar.setEnabled(false);
	    operacionCancelar.setEnabled(false);
	    
	    verificarLista();
	}
}
