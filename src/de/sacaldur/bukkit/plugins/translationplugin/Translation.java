/*
 * Copyright 2011 Sacaldur
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.sacaldur.bukkit.plugins.translationplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Translation {
	private String plugin;
	private String language;
	private HashMap<String, String> texttranslations = new HashMap<String, String>();
	private static final String fileseparator = System
			.getProperty("file.separator");
	private File translationfile;

	public Translation(String plugin, String language) {
		this.plugin = plugin;
		this.language = language;
		this.translationfile = new File("plugins" + fileseparator + plugin
				+ fileseparator + language + ".trans");
		load();
	}

	public static boolean isLanguageAvailable(String plugin, String language) {
		File file = new File("plugins" + fileseparator + plugin + fileseparator
				+ language + ".trans");
		return file.exists() && file.isFile() && file.canRead();
	}

	private void load() {
		if (isLanguageAvailable(plugin, language)) {
			Properties props = new Properties();
			try {
				props.load(new FileInputStream(translationfile));
			} catch (FileNotFoundException e) {
				System.err.println("The file '"
						+ translationfile.getAbsolutePath()
						+ "' was unable to be loaded.");
				e.printStackTrace();
			} catch (IOException e) {
				System.err
						.println("There was an IOException while reading the File '"
								+ translationfile.getAbsolutePath() + "'.");
				e.printStackTrace();
			}
			for (Object key : props.keySet()) {
				if (key instanceof String && props.get(key) instanceof String) {
					if (!texttranslations.containsKey((String) key)) {
						texttranslations.put((String) key,
								(String) props.get(key));
					}
				}
			}
		}
	}

	public boolean isTextTranslationAvailable(String name) {
		if (texttranslations.containsKey(name)) {
			return true;
		} else {
			load();
			return texttranslations.containsKey(name);
		}
	}

	public String getTextTranslation(String name) {
		if (texttranslations.containsKey(name)) {
			return texttranslations.get(name);
		} else {
			load();
			if (texttranslations.containsKey(name)) {
				return texttranslations.get(name);
			}
		}
		return null;
	}

	public String getTextTranslation(String name, String alternative) {
		String transl = getTextTranslation(name);
		return (transl == null) ? alternative : transl;
	}

	public String getPlugin() {
		return this.plugin;
	}

	public String getLanguage() {
		return this.language;
	}
}
