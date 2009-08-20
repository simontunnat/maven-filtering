package org.apache.maven.shared.filtering;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @since 1.0-beta-3
 */
public class AbstractMavenFilteringRequest
{

    private MavenProject mavenProject;

    private List filters;

    private boolean escapeWindowsPaths = true;

    private String encoding;

    private MavenSession mavenSession;

    /** 
     * List of String considered as expressions which contains values in the project/pom : pom project
     * default value will be pom and project.
     * @since 1.0-beta-2
     */
    private List projectStartExpressions = new ArrayList();
    
    /**
     * String which will escape interpolation mechanism : foo \${foo.bar} -> foo ${foo.bar}
     * @since 1.0-beta-2
     */
    private String escapeString;
    
    /**
     * @since 1.0-beta-3
     */
    private Properties additionalProperties;
    
    /**
     * @since 1.0-beta-3
     */
    private boolean injectProjectBuildFilters = false;
    
    /**
     * @since 1.0-beta-3
     */
    private Set delimiters = new HashSet();
    
    protected AbstractMavenFilteringRequest()
    {
        initDefaults();
    }

    protected AbstractMavenFilteringRequest( MavenProject mavenProject, List filters,
                                          String encoding, MavenSession mavenSession )
    {
        initDefaults();
        this.mavenProject = mavenProject;
        this.filters = filters;
        this.encoding = encoding;
        this.mavenSession = mavenSession;
    }

    private void initDefaults()
    {
        projectStartExpressions.add( "pom" );
        projectStartExpressions.add( "project" );
        
        delimiters.add( "${*}" );
        delimiters.add( "@" );
    }

    public MavenProject getMavenProject()
    {
        return mavenProject;
    }

    public void setMavenProject( MavenProject mavenProject )
    {
        this.mavenProject = mavenProject;
    }

    public List getFilters()
    {
        return filters;
    }

    public void setFilters( List filters )
    {
        this.filters = filters;
    }

    public List getFileFilters()
    {
        return getFilters();
    }

    public void setFileFilters( List filters )
    {
        setFilters( filters );
    }

    /**
     * @since 1.0-beta-3
     */
    public boolean isEscapeWindowsPaths()
    {
        return escapeWindowsPaths;
    }

    /**
     * @since 1.0-beta-3
     */
    public void setEscapeWindowsPaths( boolean escapedBackslashesInFilePath )
    {
        this.escapeWindowsPaths = escapedBackslashesInFilePath;
    }
    
    public boolean isEscapedBackslashesInFilePath()
    {
        return isEscapeWindowsPaths();
    }
    
    public void setEscapedBackslashesInFilePath( boolean escape )
    {
        setEscapeWindowsPaths( escape );
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding( String encoding )
    {
        this.encoding = encoding;
    }

    public MavenSession getMavenSession()
    {
        return mavenSession;
    }

    public void setMavenSession( MavenSession mavenSession )
    {
        this.mavenSession = mavenSession;
    }

    /**
     * @since 1.0-beta-3
     */
    public Properties getAdditionalProperties()
    {
        return additionalProperties;
    }

    /**
     * @since 1.0-beta-3
     */
    public void setAdditionalProperties( Properties additionalProperties )
    {
        this.additionalProperties = additionalProperties;
    }

    /**
     * @since 1.0-beta-3
     */
    public boolean isInjectProjectBuildFilters()
    {
        return injectProjectBuildFilters;
    }

    /**
     * @since 1.0-beta-3
     */
    public void setInjectProjectBuildFilters( boolean injectProjectBuildFilters )
    {
        this.injectProjectBuildFilters = injectProjectBuildFilters;
    }

    /**
     * @return
     * @since 1.0-beta-2
     */
    public String getEscapeString()
    {
        return escapeString;
    }

    /**
     * @param escapeString
     * @since 1.0-beta-2
     */
    public void setEscapeString( String escapeString )
    {
        this.escapeString = escapeString;
    }
    
    /**
     * @return
     * @since 1.0-beta-2
     */
    public List getProjectStartExpressions()
    {
        return projectStartExpressions;
    }

    /**
     * @param projectStartExpressions
     * @since 1.0-beta-2
     */
    public void setProjectStartExpressions( List projectStartExpressions )
    {
        this.projectStartExpressions = projectStartExpressions;
    }

    /**
     * @return Not allowed to be null or empty.
     * @since 1.0-beta-3
     */
    public Set getDelimiters()
    {
        return delimiters;
    }

    /**
     * @param delimiters If null, reset delimiters to ${*} only. Otherwise, use the provided parameter value.
     * @since 1.0-beta-3
     */
    public void setDelimiters( Set delimiters )
    {
        if ( delimiters == null || delimiters.isEmpty() )
        {
            this.delimiters.clear();
            this.delimiters.add( "${*}" );
        }
        else
        {
            this.delimiters = delimiters;
        }
    }

}
