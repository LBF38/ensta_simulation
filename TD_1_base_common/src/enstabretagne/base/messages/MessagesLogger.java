/*
 *
 */
package enstabretagne.base.messages;

import enstabretagne.base.logger.ToRecord;

// TODO: Auto-generated Javadoc

/**
 * The Class MessagesLogger.
 */
public class MessagesLogger {

    /**
     * The Constant LoggerImpossible.
     */
    public static final String LoggerImpossible = "Ne devrait pas arriver";

    /**
     * The Constant LoggerSettingsDirNotFound.
     */
    public static final String LoggerSettingsDirNotFound = "R�pertoire de configuration du logger non trouv�";

    /**
     * The Constant LoggerSettingsFileNotFound.
     */
    public static final String LoggerSettingsFileNotFound = "Fichier de configuration du logger non trouv�";

    /**
     * The Constant LoggerKindNotProvided.
     */
    public static final String LoggerKindNotProvided = "Type de logger non fourni";

    /**
     * The Constant LoggerKindNotFound.
     */
    public static final String LoggerKindNotFound = "Type de logger non trouv�";

    /**
     * The Constant LoggerParsingFailed.
     */
    public static final String LoggerParsingFailed = "Erreur de parsing du JSON";

    /**
     * The Constant LoggerDataIncorrectMethod.
     */
    public static final String LoggerDataIncorrectMethod = "La m�thode suivante devrait �tre avoir une signature de getter comme 'Object fonc()'";

    /**
     * The Constant LoggerDataReuseOfAToRecordName.
     */
    public static final String LoggerDataReuseOfAToRecordName = "Nom d�j� donn� � une m�thode par l'annotation '" + ToRecord.class.getName() + "'";

    /**
     * The Constant ExcelLoggerDirNotFound.
     */
    public static final String ExcelLoggerDirNotFound = "Nom du R�pertoire cible non trouv�";

    /**
     * The Constant ExcelLoggerFileNotFound.
     */
    public static final String ExcelLoggerFileNotFound = "Nom du Fichier cible non trouv�";

    /**
     * The Constant ExcelLoggerRecordStartTimeNotFound.
     */
    public static final String ExcelLoggerRecordStartTimeNotFound = "Heure de d�but d'enregistrement non trouv�e";
}
