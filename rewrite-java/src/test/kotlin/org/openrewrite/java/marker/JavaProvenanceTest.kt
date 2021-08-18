/*
 * Copyright 2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.java.marker

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.openrewrite.java.JavaParser

class JavaProvenanceTest {

    @Test
    fun typesFromClasspath() {
        val javaProvenance = JavaProvenance.build(
            "myproject", "main",
            JavaProvenance.BuildTool(JavaProvenance.BuildTool.Type.Gradle, "7.1.1"),
            JavaProvenance.JavaVersion("11", "11", "11", "11"),
            JavaParser.runtimeClasspath,
            JavaProvenance.Publication("org.openrewrite", "myproject", "1.0")
        )

        assertThat(javaProvenance.classpath.map { it.fullyQualifiedName })
            .contains("org.junit.jupiter.api.Test")
    }
}
