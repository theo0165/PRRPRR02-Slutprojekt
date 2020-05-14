package helpers;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class LanguageHelper {
    public static ResourceBundle getCurrentLanguage(){
        String lang = DataHelper.getCurrentLang();

        if(lang != null){
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
