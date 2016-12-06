/*******************************************************************************
 * Copyright 2016 JSL Solucoes LTDA - https://jslsolucoes.com
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
 *******************************************************************************/
package com.jslsolucoes.nginx.admin.repository.impl;

import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jslsolucoes.nginx.admin.model.ResourceIdentifier;
import com.jslsolucoes.nginx.admin.repository.ResourceIdentifierRepository;

@RequestScoped
public class ResourceIdentifierRepositoryImpl extends RepositoryImpl<ResourceIdentifier>
		implements ResourceIdentifierRepository {

	public ResourceIdentifierRepositoryImpl() {

	}

	@Inject
	public ResourceIdentifierRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public ResourceIdentifier create() {
		ResourceIdentifier resourceIdentifier = new ResourceIdentifier(UUID.randomUUID().toString());
		super.insert(resourceIdentifier);
		return resourceIdentifier;
	}

	@Override
	public void delete(String hash) {
		super.delete(findByHash(hash));
	}

	private ResourceIdentifier findByHash(String hash) {
		return (ResourceIdentifier) entityManager.createQuery("from ResourceIdentifier where hash = :hash")
				.setParameter("hash", hash).getSingleResult();
	}

}
