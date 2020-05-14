package helpers;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Language helper
 */
public class LanguageHelper {
    /**
     * Check current language and return correlating file
     * @return ResourceBundle
     */
    public static ResourceBundle getCurrentLanguage(){
        String lang = DataHelper.getCurrentLang();

        if(lang != null){ // Return english if no language is set.
            if (lang.equals("sv")){
                return ResourceBundle.getBundle("resources.lang_sv");
            }else{
                return ResourceBundle.getBundle("resources.lang");
            }
        }else{
            return ResourceBundle.getBundle("resources.lang");
        }
    }
}
