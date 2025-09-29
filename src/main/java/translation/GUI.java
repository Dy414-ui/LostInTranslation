package translation;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;


// TODO Task D: Update the GUI for the program to align with UI shown in the README example.
//            Currently, the program only uses the CanadaTranslator and the user has
//            to manually enter the language code they want to use for the translation.
//            See the examples package for some code snippets that may be useful when updating
//            the GUI.
public class GUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Code Converters
            JSONTranslator translator = new JSONTranslator();
            CountryCodeConverter countryConverter = new CountryCodeConverter();
            LanguageCodeConverter languageConverter = new LanguageCodeConverter();

            // Select country
            JPanel countryPanel = new JPanel();
            JComboBox<String> countryBox = new JComboBox<>(
                    translator.getCountryCodes().stream()
                            .map(countryConverter::fromCountryCode)
                            .toArray(String[]::new)
            );
            countryPanel.add(new JLabel("Country:"));
            countryPanel.add(countryBox);

            // Select language
            JPanel languagePanel = new JPanel();
            JComboBox<String> languageBox = new JComboBox<>(
                    translator.getLanguageCodes().stream()
                            .map(languageConverter::fromLanguageCode)
                            .toArray(String[]::new)
            );
            languagePanel.add(new JLabel("Language:"));
            languagePanel.add(languageBox);

            // Submit button
            JPanel buttonPanel = new JPanel();
            JButton submit = new JButton("Submit");
            buttonPanel.add(submit);

            // Display translation
            JLabel resultLabelText = new JLabel("Translation:");
            buttonPanel.add(resultLabelText);
            JLabel resultLabel = new JLabel("\t\t\t\t\t\t\t");
            buttonPanel.add(resultLabel);


            // Adding listener for when the user clicks the submit button
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String language = (String) languageBox.getSelectedItem();
                    String country = (String) countryBox.getSelectedItem();

                    String countryCode = countryConverter.fromCountry(country);
                    String languageCode = languageConverter.fromLanguage(language);

                    String result = translator.translate(countryCode, languageCode);
                    if (result == null) {
                        result = "no translation found!";
                    }
                    resultLabel.setText(result);
                }
            });


            // Main Panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(countryPanel);
            mainPanel.add(languagePanel);
            mainPanel.add(buttonPanel);

            // Frame Setup
            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });
    }
}
