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

/**
 * An interface for translation plugins. The behaviour of implementations may be
 * different. The translations may are stored in a seperate file and not
 * dynamically generated. The translations may are stored temporarly in a cache.
 * 
 * @author Sacaldur
 * @version 0.1.0
 * 
 */
public interface ITranslationPlugin {

	/**
	 * Returns the translation for the given text name or <code>null</code>.
	 * 
	 * @param plugin
	 *            the name of plugin which calls this method (without package
	 *            name)
	 * @param language
	 *            the target languages language code
	 * @param name
	 *            the text name
	 * @return the translation or <code>null</code>.
	 */
	public abstract String getTextTranslation(String plugin, String name);
}
