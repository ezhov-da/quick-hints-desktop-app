package ru.ezhov.regularexpression.frame;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import ru.ezhov.regularexpression.listeners.ListenerButtonCorrect;
import ru.ezhov.regularexpression.listeners.ListenerButtonDelete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author RRNDeonisiusEZH
 */
public class LookAndDeletePanel extends JPanel {
    private JPanel panel;
    private JTextPane text;
    private CircleButton correct;
    private CircleButton delete;
    private JScrollPane scrollPane;
    private Frame owner;
    private PanelEngine panelEngine;

    public LookAndDeletePanel() {
        init();
    }

    private void init() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        setLayout(new BorderLayout());

        panel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane();
        text = new JTextPane();
        scrollPane.setViewportView(text);

        delete = new CircleButton();
        delete.setIcon(SettingsFrame.DELETE);
        delete.setAlignmentX(CircleButton.CENTER_ALIGNMENT);

        correct = new CircleButton();
        correct.addMouseListener(new ListenerButtonCorrect());
        correct.setText("edit");
        correct.setAlignmentX(CircleButton.CENTER_ALIGNMENT);

        text.setEditable(false);

        delete.addMouseListener(new ListenerButtonDelete());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButton = new JPanel(new BorderLayout());
        JPanel panelButtonEdit = new JPanel();
        panelButtonEdit.add(correct);

        JPanel panelButtonDelete = new JPanel();
        panelButtonDelete.add(delete);
        panelButton.add(panelButtonEdit, BorderLayout.CENTER);
        panelButton.add(panelButtonDelete, BorderLayout.EAST);

        panel.add(panelButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    public void addPanelEngine(List<String> words, String text) {
        if (panelEngine != null) {
            remove(panelEngine);
            revalidate();
        }
        panelEngine = new PanelEngine(words, text);
        add(panelEngine, BorderLayout.NORTH);
    }

    public void removePanelEngine() {
        if (panelEngine != null) {
            remove(panelEngine);
            revalidate();
        }
    }

    public JTextPane getText() {
        return text;
    }

    public CircleButton getDelete() {
        return delete;
    }

    private class PanelEngine extends JPanel {
        private List<String> words;
        private Map<String, JTextField> map = new HashMap<String, JTextField>();
        private JButton button = new JButton("apply template");

        private String text;

        public PanelEngine(List<String> words, String text) {
            this.words = words;
            this.text = text;
            setLayout(new BorderLayout());

            JPanel panelTextField = new JPanel(new GridLayout(words.size(), 1));

            panelTextField.setLayout(new GridLayout(words.size(), 1));

            for (String s : words) {
                JTextField textField = new ExtendsJTextField(s);
                map.put(s, textField);
                panelTextField.add(textField);
            }

            add(panelTextField, BorderLayout.NORTH);

            JPanel panelAddButton = new JPanel();
            panelAddButton.add(button);

            add(panelAddButton, BorderLayout.CENTER);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Initialize the engine.
                    VelocityEngine engine = new VelocityEngine();
                    engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
//					engine.setProperty("runtime.log.logsystem.log4j.logger", LOGGER.getName());
                    engine.setProperty(Velocity.RESOURCE_LOADER, "string");
                    engine.addProperty("string.resource.loader.class", StringResourceLoader.class.getName());
                    engine.addProperty("string.resource.loader.repository.static", "false");
                    //  engine.addProperty("string.resource.loader.modificationCheckInterval", "1");
                    engine.init();

                    // Initialize my template repository. You can replace the "Hello $w" with your String.
                    StringResourceRepository repo = (StringResourceRepository) engine.getApplicationAttribute(StringResourceLoader.REPOSITORY_NAME_DEFAULT);
                    repo.putStringResource("template", PanelEngine.this.text);

                    // Set parameters for my template.
                    VelocityContext context = new VelocityContext();
                    Set<Map.Entry<String, JTextField>> entries = map.entrySet();
                    for (Map.Entry<String, JTextField> entry : entries) {
                        String key = entry.getKey().replaceAll("\\$", "");
                        context.put(key, entry.getValue().getText());
                    }

                    // Get and merge the template with my parameters.
                    Template template = engine.getTemplate("template", "UTF-8");
                    StringWriter writer = new StringWriter();
                    template.merge(context, writer);

                    // Show the result.
                    System.out.println(writer.toString());


                    JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);
                    dialog.setModal(true);
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    JTextPane textPane = new JTextPane();
                    textPane.setText(writer.toString());
                    dialog.add(new JScrollPane(textPane));
                    dialog.setSize(400, 400);
                    dialog.setLocationRelativeTo(LookAndDeletePanel.this);
                    dialog.setVisible(true);
                }
            });
        }
    }

}
