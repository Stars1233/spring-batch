/*
 * Copyright 2006-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.batch.core.configuration.support;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * A {@link JobFactory} that creates its own {@link ApplicationContext} and pulls a bean
 * out when asked to create a {@link Job}.
 *
 * @author Dave Syer
 * @author Mahmoud Ben Hassine
 * @deprecated since 6.0 with no replacement. Scheduled for removal in 6.2 or later.
 *
 */
@Deprecated(since = "6.0", forRemoval = true)
public class ApplicationContextJobFactory implements JobFactory {

	private final Job job;

	/**
	 * @param jobName the ID of the {@link Job} in the application context to be created.
	 * @param applicationContextFactory a factory for an application context that contains
	 * a job with the job name provided.
	 */
	public ApplicationContextJobFactory(String jobName, ApplicationContextFactory applicationContextFactory) {
		ConfigurableApplicationContext context = applicationContextFactory.createApplicationContext();
		this.job = context.getBean(jobName, Job.class);
	}

	/**
	 * Create an {@link ApplicationContext} from the factory provided and pull out a bean
	 * with the name given during initialization.
	 *
	 * @see org.springframework.batch.core.configuration.JobFactory#createJob()
	 */
	@Override
	public final Job createJob() {
		return job;
	}

	/**
	 * Return the name of the instance passed in on initialization.
	 *
	 * @see JobFactory#getJobName()
	 */
	@Override
	public String getJobName() {
		return job.getName();
	}

}
