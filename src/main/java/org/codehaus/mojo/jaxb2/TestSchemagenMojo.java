package org.codehaus.mojo.jaxb2;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.util.List;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.project.MavenProject;

/**
 * Creates XML Schema file(s) for test sources.
 *
 * @author rfscholte
 * @goal testSchemagen
 * @phase generate-test-resources
 * @requiresDependencyResolution test
 * @since 1.3
 */
public class TestSchemagenMojo
    extends AbstractSchemagenMojo
{

    /**
     * The source directories containing the test sources to be compiled.
     *
     * @parameter expression="${project.testCompileSourceRoots}"
     * @required
     * @readonly
     */
    private List<String> compileSourceRoots;

    /**
     * The working directory to place processor and javac generated class files.
     *
     * @parameter default-value="${project.build.directory}/generated-test-resources/schemagen"
     * @required
     */
    private File outputDirectory;
    
    /**
     * The name of the directory where copies of the original/generated
     * schema files are stored. Thus, original generated XSD files
     * are preserved for reference.
     *
     * @parameter default-value="${project.build.directory}/jaxb2/test-work"
     */
    private File testWorkDirectory;

    @Override
    protected File getOutputDirectory()
    {
        return outputDirectory;
    }
    
    @Override
    protected File getWorkDirectory()
    {
        return testWorkDirectory;
    }

    @Override
    protected List<String> getCompileSourceRoots()
    {
        return compileSourceRoots;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    protected List<String> getClasspathElements( MavenProject project )
        throws DependencyResolutionRequiredException
    {
        return project.getTestClasspathElements();
    }
}
