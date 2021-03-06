/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchMembershipRequestException;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.impl.MembershipRequestImpl;
import com.liferay.portal.model.impl.MembershipRequestModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the membership request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipRequestPersistence
 * @see MembershipRequestUtil
 * @generated
 */
public class MembershipRequestPersistenceImpl extends BasePersistenceImpl<MembershipRequest>
	implements MembershipRequestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MembershipRequestUtil} to access the membership request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MembershipRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			MembershipRequestModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			MembershipRequestModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			MembershipRequestModelImpl.GROUPID_COLUMN_BITMASK |
			MembershipRequestModelImpl.STATUSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			MembershipRequestModelImpl.GROUPID_COLUMN_BITMASK |
			MembershipRequestModelImpl.USERID_COLUMN_BITMASK |
			MembershipRequestModelImpl.STATUSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the membership request in the entity cache if it is enabled.
	 *
	 * @param membershipRequest the membership request
	 */
	public void cacheResult(MembershipRequest membershipRequest) {
		EntityCacheUtil.putResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestImpl.class, membershipRequest.getPrimaryKey(),
			membershipRequest);

		membershipRequest.resetOriginalValues();
	}

	/**
	 * Caches the membership requests in the entity cache if it is enabled.
	 *
	 * @param membershipRequests the membership requests
	 */
	public void cacheResult(List<MembershipRequest> membershipRequests) {
		for (MembershipRequest membershipRequest : membershipRequests) {
			if (EntityCacheUtil.getResult(
						MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
						MembershipRequestImpl.class,
						membershipRequest.getPrimaryKey()) == null) {
				cacheResult(membershipRequest);
			}
			else {
				membershipRequest.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all membership requests.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MembershipRequestImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MembershipRequestImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the membership request.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MembershipRequest membershipRequest) {
		EntityCacheUtil.removeResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestImpl.class, membershipRequest.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MembershipRequest> membershipRequests) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MembershipRequest membershipRequest : membershipRequests) {
			EntityCacheUtil.removeResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
				MembershipRequestImpl.class, membershipRequest.getPrimaryKey());
		}
	}

	/**
	 * Creates a new membership request with the primary key. Does not add the membership request to the database.
	 *
	 * @param membershipRequestId the primary key for the new membership request
	 * @return the new membership request
	 */
	public MembershipRequest create(long membershipRequestId) {
		MembershipRequest membershipRequest = new MembershipRequestImpl();

		membershipRequest.setNew(true);
		membershipRequest.setPrimaryKey(membershipRequestId);

		return membershipRequest;
	}

	/**
	 * Removes the membership request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipRequestId the primary key of the membership request
	 * @return the membership request that was removed
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest remove(long membershipRequestId)
		throws NoSuchMembershipRequestException, SystemException {
		return remove(Long.valueOf(membershipRequestId));
	}

	/**
	 * Removes the membership request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the membership request
	 * @return the membership request that was removed
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MembershipRequest remove(Serializable primaryKey)
		throws NoSuchMembershipRequestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MembershipRequest membershipRequest = (MembershipRequest)session.get(MembershipRequestImpl.class,
					primaryKey);

			if (membershipRequest == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMembershipRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(membershipRequest);
		}
		catch (NoSuchMembershipRequestException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MembershipRequest removeImpl(MembershipRequest membershipRequest)
		throws SystemException {
		membershipRequest = toUnwrappedModel(membershipRequest);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(membershipRequest)) {
				membershipRequest = (MembershipRequest)session.get(MembershipRequestImpl.class,
						membershipRequest.getPrimaryKeyObj());
			}

			if (membershipRequest != null) {
				session.delete(membershipRequest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (membershipRequest != null) {
			clearCache(membershipRequest);
		}

		return membershipRequest;
	}

	@Override
	public MembershipRequest updateImpl(
		com.liferay.portal.model.MembershipRequest membershipRequest)
		throws SystemException {
		membershipRequest = toUnwrappedModel(membershipRequest);

		boolean isNew = membershipRequest.isNew();

		MembershipRequestModelImpl membershipRequestModelImpl = (MembershipRequestModelImpl)membershipRequest;

		Session session = null;

		try {
			session = openSession();

			if (membershipRequest.isNew()) {
				session.save(membershipRequest);

				membershipRequest.setNew(false);
			}
			else {
				session.merge(membershipRequest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MembershipRequestModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((membershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(membershipRequestModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						Long.valueOf(membershipRequestModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((membershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(membershipRequestModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(membershipRequestModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((membershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(membershipRequestModelImpl.getOriginalGroupId()),
						Integer.valueOf(membershipRequestModelImpl.getOriginalStatusId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);

				args = new Object[] {
						Long.valueOf(membershipRequestModelImpl.getGroupId()),
						Integer.valueOf(membershipRequestModelImpl.getStatusId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);
			}

			if ((membershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(membershipRequestModelImpl.getOriginalGroupId()),
						Long.valueOf(membershipRequestModelImpl.getOriginalUserId()),
						Integer.valueOf(membershipRequestModelImpl.getOriginalStatusId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
					args);

				args = new Object[] {
						Long.valueOf(membershipRequestModelImpl.getGroupId()),
						Long.valueOf(membershipRequestModelImpl.getUserId()),
						Integer.valueOf(membershipRequestModelImpl.getStatusId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
					args);
			}
		}

		EntityCacheUtil.putResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestImpl.class, membershipRequest.getPrimaryKey(),
			membershipRequest);

		return membershipRequest;
	}

	protected MembershipRequest toUnwrappedModel(
		MembershipRequest membershipRequest) {
		if (membershipRequest instanceof MembershipRequestImpl) {
			return membershipRequest;
		}

		MembershipRequestImpl membershipRequestImpl = new MembershipRequestImpl();

		membershipRequestImpl.setNew(membershipRequest.isNew());
		membershipRequestImpl.setPrimaryKey(membershipRequest.getPrimaryKey());

		membershipRequestImpl.setMembershipRequestId(membershipRequest.getMembershipRequestId());
		membershipRequestImpl.setGroupId(membershipRequest.getGroupId());
		membershipRequestImpl.setCompanyId(membershipRequest.getCompanyId());
		membershipRequestImpl.setUserId(membershipRequest.getUserId());
		membershipRequestImpl.setCreateDate(membershipRequest.getCreateDate());
		membershipRequestImpl.setComments(membershipRequest.getComments());
		membershipRequestImpl.setReplyComments(membershipRequest.getReplyComments());
		membershipRequestImpl.setReplyDate(membershipRequest.getReplyDate());
		membershipRequestImpl.setReplierUserId(membershipRequest.getReplierUserId());
		membershipRequestImpl.setStatusId(membershipRequest.getStatusId());

		return membershipRequestImpl;
	}

	/**
	 * Returns the membership request with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the membership request
	 * @return the membership request
	 * @throws com.liferay.portal.NoSuchModelException if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MembershipRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the membership request with the primary key or throws a {@link com.liferay.portal.NoSuchMembershipRequestException} if it could not be found.
	 *
	 * @param membershipRequestId the primary key of the membership request
	 * @return the membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByPrimaryKey(long membershipRequestId)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByPrimaryKey(membershipRequestId);

		if (membershipRequest == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					membershipRequestId);
			}

			throw new NoSuchMembershipRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				membershipRequestId);
		}

		return membershipRequest;
	}

	/**
	 * Returns the membership request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the membership request
	 * @return the membership request, or <code>null</code> if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MembershipRequest fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the membership request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipRequestId the primary key of the membership request
	 * @return the membership request, or <code>null</code> if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByPrimaryKey(long membershipRequestId)
		throws SystemException {
		MembershipRequest membershipRequest = (MembershipRequest)EntityCacheUtil.getResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
				MembershipRequestImpl.class, membershipRequestId);

		if (membershipRequest == _nullMembershipRequest) {
			return null;
		}

		if (membershipRequest == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				membershipRequest = (MembershipRequest)session.get(MembershipRequestImpl.class,
						Long.valueOf(membershipRequestId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (membershipRequest != null) {
					cacheResult(membershipRequest);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
						MembershipRequestImpl.class, membershipRequestId,
						_nullMembershipRequest);
				}

				closeSession(session);
			}
		}

		return membershipRequest;
	}

	/**
	 * Returns all the membership requests where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByGroupId(long groupId, int start,
		int end) throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<MembershipRequest> list = (List<MembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MembershipRequest membershipRequest : list) {
				if ((groupId != membershipRequest.getGroupId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<MembershipRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByGroupId_First(groupId,
				orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MembershipRequest> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		List<MembershipRequest> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63;.
	 *
	 * @param membershipRequestId the primary key of the current membership request
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest[] findByGroupId_PrevAndNext(
		long membershipRequestId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = findByPrimaryKey(membershipRequestId);

		Session session = null;

		try {
			session = openSession();

			MembershipRequest[] array = new MembershipRequestImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, membershipRequest,
					groupId, orderByComparator, true);

			array[1] = membershipRequest;

			array[2] = getByGroupId_PrevAndNext(session, membershipRequest,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MembershipRequest getByGroupId_PrevAndNext(Session session,
		MembershipRequest membershipRequest, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the membership requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByUserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<MembershipRequest> list = (List<MembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MembershipRequest membershipRequest : list) {
				if ((userId != membershipRequest.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<MembershipRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first membership request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByUserId_First(userId,
				orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first membership request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MembershipRequest> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByUserId_Last(userId,
				orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last membership request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<MembershipRequest> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership requests before and after the current membership request in the ordered set where userId = &#63;.
	 *
	 * @param membershipRequestId the primary key of the current membership request
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest[] findByUserId_PrevAndNext(
		long membershipRequestId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = findByPrimaryKey(membershipRequestId);

		Session session = null;

		try {
			session = openSession();

			MembershipRequest[] array = new MembershipRequestImpl[3];

			array[0] = getByUserId_PrevAndNext(session, membershipRequest,
					userId, orderByComparator, true);

			array[1] = membershipRequest;

			array[2] = getByUserId_PrevAndNext(session, membershipRequest,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MembershipRequest getByUserId_PrevAndNext(Session session,
		MembershipRequest membershipRequest, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @return the matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByG_S(long groupId, int statusId)
		throws SystemException {
		return findByG_S(groupId, statusId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByG_S(long groupId, int statusId,
		int start, int end) throws SystemException {
		return findByG_S(groupId, statusId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByG_S(long groupId, int statusId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] { groupId, statusId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] {
					groupId, statusId,
					
					start, end, orderByComparator
				};
		}

		List<MembershipRequest> list = (List<MembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MembershipRequest membershipRequest : list) {
				if ((groupId != membershipRequest.getGroupId()) ||
						(statusId != membershipRequest.getStatusId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(statusId);

				list = (List<MembershipRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByG_S_First(long groupId, int statusId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByG_S_First(groupId,
				statusId, orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", statusId=");
		msg.append(statusId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByG_S_First(long groupId, int statusId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MembershipRequest> list = findByG_S(groupId, statusId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByG_S_Last(long groupId, int statusId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByG_S_Last(groupId,
				statusId, orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", statusId=");
		msg.append(statusId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByG_S_Last(long groupId, int statusId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_S(groupId, statusId);

		List<MembershipRequest> list = findByG_S(groupId, statusId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param membershipRequestId the primary key of the current membership request
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest[] findByG_S_PrevAndNext(long membershipRequestId,
		long groupId, int statusId, OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = findByPrimaryKey(membershipRequestId);

		Session session = null;

		try {
			session = openSession();

			MembershipRequest[] array = new MembershipRequestImpl[3];

			array[0] = getByG_S_PrevAndNext(session, membershipRequest,
					groupId, statusId, orderByComparator, true);

			array[1] = membershipRequest;

			array[2] = getByG_S_PrevAndNext(session, membershipRequest,
					groupId, statusId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MembershipRequest getByG_S_PrevAndNext(Session session,
		MembershipRequest membershipRequest, long groupId, int statusId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUSID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(statusId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @return the matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByG_U_S(long groupId, long userId,
		int statusId) throws SystemException {
		return findByG_U_S(groupId, userId, statusId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByG_U_S(long groupId, long userId,
		int statusId, int start, int end) throws SystemException {
		return findByG_U_S(groupId, userId, statusId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findByG_U_S(long groupId, long userId,
		int statusId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S;
			finderArgs = new Object[] { groupId, userId, statusId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S;
			finderArgs = new Object[] {
					groupId, userId, statusId,
					
					start, end, orderByComparator
				};
		}

		List<MembershipRequest> list = (List<MembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MembershipRequest membershipRequest : list) {
				if ((groupId != membershipRequest.getGroupId()) ||
						(userId != membershipRequest.getUserId()) ||
						(statusId != membershipRequest.getStatusId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(statusId);

				list = (List<MembershipRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByG_U_S_First(long groupId, long userId,
		int statusId, OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByG_U_S_First(groupId,
				userId, statusId, orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", statusId=");
		msg.append(statusId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByG_U_S_First(long groupId, long userId,
		int statusId, OrderByComparator orderByComparator)
		throws SystemException {
		List<MembershipRequest> list = findByG_U_S(groupId, userId, statusId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest findByG_U_S_Last(long groupId, long userId,
		int statusId, OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = fetchByG_U_S_Last(groupId,
				userId, statusId, orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", statusId=");
		msg.append(statusId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest fetchByG_U_S_Last(long groupId, long userId,
		int statusId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByG_U_S(groupId, userId, statusId);

		List<MembershipRequest> list = findByG_U_S(groupId, userId, statusId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param membershipRequestId the primary key of the current membership request
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership request
	 * @throws com.liferay.portal.NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MembershipRequest[] findByG_U_S_PrevAndNext(
		long membershipRequestId, long groupId, long userId, int statusId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipRequestException, SystemException {
		MembershipRequest membershipRequest = findByPrimaryKey(membershipRequestId);

		Session session = null;

		try {
			session = openSession();

			MembershipRequest[] array = new MembershipRequestImpl[3];

			array[0] = getByG_U_S_PrevAndNext(session, membershipRequest,
					groupId, userId, statusId, orderByComparator, true);

			array[1] = membershipRequest;

			array[2] = getByG_U_S_PrevAndNext(session, membershipRequest,
					groupId, userId, statusId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MembershipRequest getByG_U_S_PrevAndNext(Session session,
		MembershipRequest membershipRequest, long groupId, long userId,
		int statusId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUSID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(statusId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the membership requests.
	 *
	 * @return the membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MembershipRequest> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MembershipRequest> list = (List<MembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MEMBERSHIPREQUEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEMBERSHIPREQUEST.concat(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MembershipRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MembershipRequest>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the membership requests where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (MembershipRequest membershipRequest : findByGroupId(groupId)) {
			remove(membershipRequest);
		}
	}

	/**
	 * Removes all the membership requests where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (MembershipRequest membershipRequest : findByUserId(userId)) {
			remove(membershipRequest);
		}
	}

	/**
	 * Removes all the membership requests where groupId = &#63; and statusId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_S(long groupId, int statusId)
		throws SystemException {
		for (MembershipRequest membershipRequest : findByG_S(groupId, statusId)) {
			remove(membershipRequest);
		}
	}

	/**
	 * Removes all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_U_S(long groupId, long userId, int statusId)
		throws SystemException {
		for (MembershipRequest membershipRequest : findByG_U_S(groupId, userId,
				statusId)) {
			remove(membershipRequest);
		}
	}

	/**
	 * Removes all the membership requests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MembershipRequest membershipRequest : findAll()) {
			remove(membershipRequest);
		}
	}

	/**
	 * Returns the number of membership requests where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of membership requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @return the number of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_S(long groupId, int statusId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, statusId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(statusId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @return the number of matching membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_U_S(long groupId, long userId, int statusId)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, userId, statusId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(statusId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of membership requests.
	 *
	 * @return the number of membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEMBERSHIPREQUEST);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the membership request persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.MembershipRequest")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MembershipRequest>> listenersList = new ArrayList<ModelListener<MembershipRequest>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MembershipRequest>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(MembershipRequestImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AccountPersistence.class)
	protected AccountPersistence accountPersistence;
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = BrowserTrackerPersistence.class)
	protected BrowserTrackerPersistence browserTrackerPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ClusterGroupPersistence.class)
	protected ClusterGroupPersistence clusterGroupPersistence;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = ContactPersistence.class)
	protected ContactPersistence contactPersistence;
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = EmailAddressPersistence.class)
	protected EmailAddressPersistence emailAddressPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = LayoutBranchPersistence.class)
	protected LayoutBranchPersistence layoutBranchPersistence;
	@BeanReference(type = LayoutPrototypePersistence.class)
	protected LayoutPrototypePersistence layoutPrototypePersistence;
	@BeanReference(type = LayoutRevisionPersistence.class)
	protected LayoutRevisionPersistence layoutRevisionPersistence;
	@BeanReference(type = LayoutSetPersistence.class)
	protected LayoutSetPersistence layoutSetPersistence;
	@BeanReference(type = LayoutSetBranchPersistence.class)
	protected LayoutSetBranchPersistence layoutSetBranchPersistence;
	@BeanReference(type = LayoutSetPrototypePersistence.class)
	protected LayoutSetPrototypePersistence layoutSetPrototypePersistence;
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = LockPersistence.class)
	protected LockPersistence lockPersistence;
	@BeanReference(type = MembershipRequestPersistence.class)
	protected MembershipRequestPersistence membershipRequestPersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = OrgGroupRolePersistence.class)
	protected OrgGroupRolePersistence orgGroupRolePersistence;
	@BeanReference(type = OrgLaborPersistence.class)
	protected OrgLaborPersistence orgLaborPersistence;
	@BeanReference(type = PasswordPolicyPersistence.class)
	protected PasswordPolicyPersistence passwordPolicyPersistence;
	@BeanReference(type = PasswordPolicyRelPersistence.class)
	protected PasswordPolicyRelPersistence passwordPolicyRelPersistence;
	@BeanReference(type = PasswordTrackerPersistence.class)
	protected PasswordTrackerPersistence passwordTrackerPersistence;
	@BeanReference(type = PhonePersistence.class)
	protected PhonePersistence phonePersistence;
	@BeanReference(type = PluginSettingPersistence.class)
	protected PluginSettingPersistence pluginSettingPersistence;
	@BeanReference(type = PortalPreferencesPersistence.class)
	protected PortalPreferencesPersistence portalPreferencesPersistence;
	@BeanReference(type = PortletPersistence.class)
	protected PortletPersistence portletPersistence;
	@BeanReference(type = PortletItemPersistence.class)
	protected PortletItemPersistence portletItemPersistence;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = RegionPersistence.class)
	protected RegionPersistence regionPersistence;
	@BeanReference(type = ReleasePersistence.class)
	protected ReleasePersistence releasePersistence;
	@BeanReference(type = RepositoryPersistence.class)
	protected RepositoryPersistence repositoryPersistence;
	@BeanReference(type = RepositoryEntryPersistence.class)
	protected RepositoryEntryPersistence repositoryEntryPersistence;
	@BeanReference(type = ResourceActionPersistence.class)
	protected ResourceActionPersistence resourceActionPersistence;
	@BeanReference(type = ResourceBlockPersistence.class)
	protected ResourceBlockPersistence resourceBlockPersistence;
	@BeanReference(type = ResourceBlockPermissionPersistence.class)
	protected ResourceBlockPermissionPersistence resourceBlockPermissionPersistence;
	@BeanReference(type = ResourcePermissionPersistence.class)
	protected ResourcePermissionPersistence resourcePermissionPersistence;
	@BeanReference(type = ResourceTypePermissionPersistence.class)
	protected ResourceTypePermissionPersistence resourceTypePermissionPersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = ServiceComponentPersistence.class)
	protected ServiceComponentPersistence serviceComponentPersistence;
	@BeanReference(type = ShardPersistence.class)
	protected ShardPersistence shardPersistence;
	@BeanReference(type = SubscriptionPersistence.class)
	protected SubscriptionPersistence subscriptionPersistence;
	@BeanReference(type = TeamPersistence.class)
	protected TeamPersistence teamPersistence;
	@BeanReference(type = TicketPersistence.class)
	protected TicketPersistence ticketPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;
	@BeanReference(type = UserGroupGroupRolePersistence.class)
	protected UserGroupGroupRolePersistence userGroupGroupRolePersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	@BeanReference(type = UserIdMapperPersistence.class)
	protected UserIdMapperPersistence userIdMapperPersistence;
	@BeanReference(type = UserNotificationEventPersistence.class)
	protected UserNotificationEventPersistence userNotificationEventPersistence;
	@BeanReference(type = UserTrackerPersistence.class)
	protected UserTrackerPersistence userTrackerPersistence;
	@BeanReference(type = UserTrackerPathPersistence.class)
	protected UserTrackerPathPersistence userTrackerPathPersistence;
	@BeanReference(type = VirtualHostPersistence.class)
	protected VirtualHostPersistence virtualHostPersistence;
	@BeanReference(type = WebDAVPropsPersistence.class)
	protected WebDAVPropsPersistence webDAVPropsPersistence;
	@BeanReference(type = WebsitePersistence.class)
	protected WebsitePersistence websitePersistence;
	@BeanReference(type = WorkflowDefinitionLinkPersistence.class)
	protected WorkflowDefinitionLinkPersistence workflowDefinitionLinkPersistence;
	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	private static final String _SQL_SELECT_MEMBERSHIPREQUEST = "SELECT membershipRequest FROM MembershipRequest membershipRequest";
	private static final String _SQL_SELECT_MEMBERSHIPREQUEST_WHERE = "SELECT membershipRequest FROM MembershipRequest membershipRequest WHERE ";
	private static final String _SQL_COUNT_MEMBERSHIPREQUEST = "SELECT COUNT(membershipRequest) FROM MembershipRequest membershipRequest";
	private static final String _SQL_COUNT_MEMBERSHIPREQUEST_WHERE = "SELECT COUNT(membershipRequest) FROM MembershipRequest membershipRequest WHERE ";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "membershipRequest.groupId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "membershipRequest.userId = ?";
	private static final String _FINDER_COLUMN_G_S_GROUPID_2 = "membershipRequest.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_STATUSID_2 = "membershipRequest.statusId = ?";
	private static final String _FINDER_COLUMN_G_U_S_GROUPID_2 = "membershipRequest.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_S_USERID_2 = "membershipRequest.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_S_STATUSID_2 = "membershipRequest.statusId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "membershipRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MembershipRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MembershipRequest exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(MembershipRequestPersistenceImpl.class);
	private static MembershipRequest _nullMembershipRequest = new MembershipRequestImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MembershipRequest> toCacheModel() {
				return _nullMembershipRequestCacheModel;
			}
		};

	private static CacheModel<MembershipRequest> _nullMembershipRequestCacheModel =
		new CacheModel<MembershipRequest>() {
			public MembershipRequest toEntityModel() {
				return _nullMembershipRequest;
			}
		};
}