/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.plugins.fontawesome

import groovyx.javafx.factory.LabeledFactory
import javafx.scene.control.Label
import javafx.scene.text.Font

/**
 * @author Andres Almiray
 */
class FontAwesomeIconFactory extends LabeledFactory {
    private static final String AWESOME_SET = 'META-INF/resources/webjars/font-awesome/3.2.1/font/fontawesome-webfont.ttf'

    static {
        Font.loadFont(Thread.currentThread().contextClassLoader.getResource(AWESOME_SET).toExternalForm(), 12)
    }

    FontAwesomeIconFactory() {
        super(Label)
    }

    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        String iconName = attributes.remove('icon') ?: value
        String iconSize = attributes.remove('size') ?: '16'

        if (!iconName) throw new IllegalArgumentException("In $name you must define a node value or icon:")
        Label label = super.newInstance(builder, name, null, attributes)
        label.styleClass << 'fontawesome-icon'
        label.text = FontAwesome.findByDescription(iconName).code
        label.style = attributes.remove('style') ?: ''
        label.style += '-fx-font-family: FontAwesome; -fx-font-size: ' + iconSize + 'px;'
        label
    }
}