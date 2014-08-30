/*
 * Copyright 2009-2014 the CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package jp.sf.fess.db.bsbhv;

import java.util.List;

import jp.sf.fess.db.bsbhv.loader.LoaderOfRequestHeader;
import jp.sf.fess.db.bsentity.dbmeta.RequestHeaderDbm;
import jp.sf.fess.db.cbean.RequestHeaderCB;
import jp.sf.fess.db.exbhv.RequestHeaderBhv;
import jp.sf.fess.db.exentity.RequestHeader;
import jp.sf.fess.db.exentity.WebCrawlingConfig;

import org.seasar.dbflute.Entity;
import org.seasar.dbflute.bhv.AbstractBehaviorWritable;
import org.seasar.dbflute.bhv.DeleteOption;
import org.seasar.dbflute.bhv.InsertOption;
import org.seasar.dbflute.bhv.QueryInsertSetupper;
import org.seasar.dbflute.bhv.ReferrerLoaderHandler;
import org.seasar.dbflute.bhv.UpdateOption;
import org.seasar.dbflute.cbean.ConditionBean;
import org.seasar.dbflute.cbean.EntityRowHandler;
import org.seasar.dbflute.cbean.ListResultBean;
import org.seasar.dbflute.cbean.PagingResultBean;
import org.seasar.dbflute.cbean.SpecifyQuery;
import org.seasar.dbflute.cbean.chelper.HpSLSExecutor;
import org.seasar.dbflute.cbean.chelper.HpSLSFunction;
import org.seasar.dbflute.dbmeta.DBMeta;
import org.seasar.dbflute.exception.BatchEntityAlreadyUpdatedException;
import org.seasar.dbflute.exception.DangerousResultSizeException;
import org.seasar.dbflute.exception.EntityAlreadyDeletedException;
import org.seasar.dbflute.exception.EntityAlreadyExistsException;
import org.seasar.dbflute.exception.EntityAlreadyUpdatedException;
import org.seasar.dbflute.exception.EntityDuplicatedException;
import org.seasar.dbflute.exception.NonQueryDeleteNotAllowedException;
import org.seasar.dbflute.exception.NonQueryUpdateNotAllowedException;
import org.seasar.dbflute.exception.SelectEntityConditionNotFoundException;
import org.seasar.dbflute.optional.OptionalEntity;
import org.seasar.dbflute.outsidesql.executor.OutsideSqlBasicExecutor;

/**
 * The behavior of REQUEST_HEADER as TABLE. <br />
 * <pre>
 * [primary key]
 *     ID
 *
 * [column]
 *     ID, NAME, VALUE, WEB_CRAWLING_CONFIG_ID, CREATED_BY, CREATED_TIME, UPDATED_BY, UPDATED_TIME, DELETED_BY, DELETED_TIME, VERSION_NO
 *
 * [sequence]
 *
 *
 * [identity]
 *     ID
 *
 * [version-no]
 *     VERSION_NO
 *
 * [foreign table]
 *     WEB_CRAWLING_CONFIG
 *
 * [referrer table]
 *
 *
 * [foreign property]
 *     webCrawlingConfig
 *
 * [referrer property]
 *
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsRequestHeaderBhv extends AbstractBehaviorWritable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /*df:beginQueryPath*/
    /*df:endQueryPath*/

    // ===================================================================================
    //                                                                          Table name
    //                                                                          ==========
    /** @return The name on database of table. (NotNull) */
    @Override
    public String getTableDbName() {
        return "REQUEST_HEADER";
    }

    // ===================================================================================
    //                                                                              DBMeta
    //                                                                              ======
    /** {@inheritDoc} */
    @Override
    public DBMeta getDBMeta() {
        return RequestHeaderDbm.getInstance();
    }

    /** @return The instance of DBMeta as my table type. (NotNull) */
    public RequestHeaderDbm getMyDBMeta() {
        return RequestHeaderDbm.getInstance();
    }

    // ===================================================================================
    //                                                                        New Instance
    //                                                                        ============
    /** {@inheritDoc} */
    @Override
    public RequestHeader newEntity() {
        return new RequestHeader();
    }

    /** {@inheritDoc} */
    @Override
    public RequestHeaderCB newConditionBean() {
        return new RequestHeaderCB();
    }

    /** @return The instance of new entity as my table type. (NotNull) */
    public RequestHeader newMyEntity() {
        return new RequestHeader();
    }

    /** @return The instance of new condition-bean as my table type. (NotNull) */
    public RequestHeaderCB newMyConditionBean() {
        return new RequestHeaderCB();
    }

    // ===================================================================================
    //                                                                        Count Select
    //                                                                        ============
    /**
     * Select the count of uniquely-selected records by the condition-bean. {IgnorePagingCondition, IgnoreSpecifyColumn}<br />
     * SpecifyColumn is ignored but you can use it only to remove text type column for union's distinct.
     * <pre>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * int count = requestHeaderBhv.<span style="color: #DD4747">selectCount</span>(cb);
     * </pre>
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @return The count for the condition. (NotMinus)
     */
    public int selectCount(final RequestHeaderCB cb) {
        return facadeSelectCount(cb);
    }

    protected int facadeSelectCount(final RequestHeaderCB cb) {
        return doSelectCountUniquely(cb);
    }

    protected int doSelectCountUniquely(final RequestHeaderCB cb) { // called by selectCount(cb)
        assertCBStateValid(cb);
        return delegateSelectCountUniquely(cb);
    }

    protected int doSelectCountPlainly(final RequestHeaderCB cb) { // called by selectPage(cb)
        assertCBStateValid(cb);
        return delegateSelectCountPlainly(cb);
    }

    @Override
    protected int doReadCount(final ConditionBean cb) {
        return facadeSelectCount(downcast(cb));
    }

    // ===================================================================================
    //                                                                       Entity Select
    //                                                                       =============
    /**
     * Select the entity by the condition-bean. #beforejava8 <br />
     * <span style="color: #AD4747; font-size: 120%">The return might be null if no data, so you should have null check.</span> <br />
     * <span style="color: #AD4747; font-size: 120%">If the data always exists as your business rule, use selectEntityWithDeletedCheck().</span>
     * <pre>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * RequestHeader requestHeader = requestHeaderBhv.<span style="color: #DD4747">selectEntity</span>(cb);
     * if (requestHeader != null) { <span style="color: #3F7E5E">// null check</span>
     *     ... = requestHeader.get...();
     * } else {
     *     ...
     * }
     * </pre>
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @return The entity selected by the condition. (NullAllowed: if no data, it returns null)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public RequestHeader selectEntity(final RequestHeaderCB cb) {
        return facadeSelectEntity(cb);
    }

    protected RequestHeader facadeSelectEntity(final RequestHeaderCB cb) {
        return doSelectEntity(cb, typeOfSelectedEntity());
    }

    protected <ENTITY extends RequestHeader> ENTITY doSelectEntity(
            final RequestHeaderCB cb, final Class<ENTITY> tp) {
        return helpSelectEntityInternally(cb, tp);
    }

    protected <ENTITY extends RequestHeader> OptionalEntity<ENTITY> doSelectOptionalEntity(
            final RequestHeaderCB cb, final Class<ENTITY> tp) {
        return createOptionalEntity(doSelectEntity(cb, tp), cb);
    }

    @Override
    protected Entity doReadEntity(final ConditionBean cb) {
        return facadeSelectEntity(downcast(cb));
    }

    /**
     * Select the entity by the condition-bean with deleted check. <br />
     * <span style="color: #AD4747; font-size: 120%">If the data always exists as your business rule, this method is good.</span>
     * <pre>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * RequestHeader requestHeader = requestHeaderBhv.<span style="color: #DD4747">selectEntityWithDeletedCheck</span>(cb);
     * ... = requestHeader.get...(); <span style="color: #3F7E5E">// the entity always be not null</span>
     * </pre>
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @return The entity selected by the condition. (NotNull: if no data, throws exception)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public RequestHeader selectEntityWithDeletedCheck(final RequestHeaderCB cb) {
        return facadeSelectEntityWithDeletedCheck(cb);
    }

    protected RequestHeader facadeSelectEntityWithDeletedCheck(
            final RequestHeaderCB cb) {
        return doSelectEntityWithDeletedCheck(cb, typeOfSelectedEntity());
    }

    protected <ENTITY extends RequestHeader> ENTITY doSelectEntityWithDeletedCheck(
            final RequestHeaderCB cb, final Class<ENTITY> tp) {
        assertCBStateValid(cb);
        assertObjectNotNull("entityType", tp);
        return helpSelectEntityWithDeletedCheckInternally(cb, tp);
    }

    @Override
    protected Entity doReadEntityWithDeletedCheck(final ConditionBean cb) {
        return facadeSelectEntityWithDeletedCheck(downcast(cb));
    }

    /**
     * Select the entity by the primary-key value.
     * @param id : PK, ID, NotNull, BIGINT(19). (NotNull)
     * @return The entity selected by the PK. (NullAllowed: if no data, it returns null)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public RequestHeader selectByPKValue(final Long id) {
        return facadeSelectByPKValue(id);
    }

    protected RequestHeader facadeSelectByPKValue(final Long id) {
        return doSelectByPK(id, typeOfSelectedEntity());
    }

    protected <ENTITY extends RequestHeader> ENTITY doSelectByPK(final Long id,
            final Class<ENTITY> tp) {
        return doSelectEntity(xprepareCBAsPK(id), tp);
    }

    protected <ENTITY extends RequestHeader> OptionalEntity<ENTITY> doSelectOptionalByPK(
            final Long id, final Class<ENTITY> tp) {
        return createOptionalEntity(doSelectByPK(id, tp), id);
    }

    /**
     * Select the entity by the primary-key value with deleted check.
     * @param id : PK, ID, NotNull, BIGINT(19). (NotNull)
     * @return The entity selected by the PK. (NotNull: if no data, throws exception)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public RequestHeader selectByPKValueWithDeletedCheck(final Long id) {
        return doSelectByPKWithDeletedCheck(id, typeOfSelectedEntity());
    }

    protected <ENTITY extends RequestHeader> ENTITY doSelectByPKWithDeletedCheck(
            final Long id, final Class<ENTITY> tp) {
        return doSelectEntityWithDeletedCheck(xprepareCBAsPK(id), tp);
    }

    protected RequestHeaderCB xprepareCBAsPK(final Long id) {
        assertObjectNotNull("id", id);
        return newConditionBean().acceptPK(id);
    }

    // ===================================================================================
    //                                                                         List Select
    //                                                                         ===========
    /**
     * Select the list as result bean.
     * <pre>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * cb.query().addOrderBy_Bar...();
     * ListResultBean&lt;RequestHeader&gt; requestHeaderList = requestHeaderBhv.<span style="color: #DD4747">selectList</span>(cb);
     * for (RequestHeader requestHeader : requestHeaderList) {
     *     ... = requestHeader.get...();
     * }
     * </pre>
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @return The result bean of selected list. (NotNull: if no data, returns empty list)
     * @exception DangerousResultSizeException When the result size is over the specified safety size.
     */
    public ListResultBean<RequestHeader> selectList(final RequestHeaderCB cb) {
        return facadeSelectList(cb);
    }

    protected ListResultBean<RequestHeader> facadeSelectList(
            final RequestHeaderCB cb) {
        return doSelectList(cb, typeOfSelectedEntity());
    }

    protected <ENTITY extends RequestHeader> ListResultBean<ENTITY> doSelectList(
            final RequestHeaderCB cb, final Class<ENTITY> tp) {
        return helpSelectListInternally(cb, tp);
    }

    @Override
    protected ListResultBean<? extends Entity> doReadList(final ConditionBean cb) {
        return facadeSelectList(downcast(cb));
    }

    // ===================================================================================
    //                                                                         Page Select
    //                                                                         ===========
    /**
     * Select the page as result bean. <br />
     * (both count-select and paging-select are executed)
     * <pre>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * cb.query().addOrderBy_Bar...();
     * cb.<span style="color: #DD4747">paging</span>(20, 3); <span style="color: #3F7E5E">// 20 records per a page and current page number is 3</span>
     * PagingResultBean&lt;RequestHeader&gt; page = requestHeaderBhv.<span style="color: #DD4747">selectPage</span>(cb);
     * int allRecordCount = page.getAllRecordCount();
     * int allPageCount = page.getAllPageCount();
     * boolean isExistPrePage = page.isExistPrePage();
     * boolean isExistNextPage = page.isExistNextPage();
     * ...
     * for (RequestHeader requestHeader : page) {
     *     ... = requestHeader.get...();
     * }
     * </pre>
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @return The result bean of selected page. (NotNull: if no data, returns bean as empty list)
     * @exception DangerousResultSizeException When the result size is over the specified safety size.
     */
    public PagingResultBean<RequestHeader> selectPage(final RequestHeaderCB cb) {
        return facadeSelectPage(cb);
    }

    protected PagingResultBean<RequestHeader> facadeSelectPage(
            final RequestHeaderCB cb) {
        return doSelectPage(cb, typeOfSelectedEntity());
    }

    protected <ENTITY extends RequestHeader> PagingResultBean<ENTITY> doSelectPage(
            final RequestHeaderCB cb, final Class<ENTITY> tp) {
        return helpSelectPageInternally(cb, tp);
    }

    @Override
    protected PagingResultBean<? extends Entity> doReadPage(
            final ConditionBean cb) {
        return facadeSelectPage(downcast(cb));
    }

    // ===================================================================================
    //                                                                       Cursor Select
    //                                                                       =============
    /**
     * Select the cursor by the condition-bean.
     * <pre>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * requestHeaderBhv.<span style="color: #DD4747">selectCursor</span>(cb, new EntityRowHandler&lt;RequestHeader&gt;() {
     *     public void handle(RequestHeader entity) {
     *         ... = entity.getFoo...();
     *     }
     * });
     * </pre>
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @param entityRowHandler The handler of entity row of RequestHeader. (NotNull)
     */
    public void selectCursor(final RequestHeaderCB cb,
            final EntityRowHandler<RequestHeader> entityRowHandler) {
        facadeSelectCursor(cb, entityRowHandler);
    }

    protected void facadeSelectCursor(final RequestHeaderCB cb,
            final EntityRowHandler<RequestHeader> entityRowHandler) {
        doSelectCursor(cb, entityRowHandler, typeOfSelectedEntity());
    }

    protected <ENTITY extends RequestHeader> void doSelectCursor(
            final RequestHeaderCB cb, final EntityRowHandler<ENTITY> handler,
            final Class<ENTITY> tp) {
        assertCBStateValid(cb);
        assertObjectNotNull("entityRowHandler", handler);
        assertObjectNotNull("entityType", tp);
        assertSpecifyDerivedReferrerEntityProperty(cb, tp);
        helpSelectCursorInternally(cb, handler, tp);
    }

    // ===================================================================================
    //                                                                       Scalar Select
    //                                                                       =============
    /**
     * Select the scalar value derived by a function from uniquely-selected records. <br />
     * You should call a function method after this method called like as follows:
     * <pre>
     * requestHeaderBhv.<span style="color: #DD4747">scalarSelect</span>(Date.class).max(new ScalarQuery() {
     *     public void query(RequestHeaderCB cb) {
     *         cb.specify().<span style="color: #DD4747">columnFooDatetime()</span>; <span style="color: #3F7E5E">// required for a function</span>
     *         cb.query().setBarName_PrefixSearch("S");
     *     }
     * });
     * </pre>
     * @param <RESULT> The type of result.
     * @param resultType The type of result. (NotNull)
     * @return The scalar function object to specify function for scalar value. (NotNull)
     */
    public <RESULT> HpSLSFunction<RequestHeaderCB, RESULT> scalarSelect(
            final Class<RESULT> resultType) {
        return facadeScalarSelect(resultType);
    }

    protected <RESULT> HpSLSFunction<RequestHeaderCB, RESULT> facadeScalarSelect(
            final Class<RESULT> resultType) {
        return doScalarSelect(resultType, newConditionBean());
    }

    protected <RESULT, CB extends RequestHeaderCB> HpSLSFunction<CB, RESULT> doScalarSelect(
            final Class<RESULT> tp, final CB cb) {
        assertObjectNotNull("resultType", tp);
        assertCBStateValid(cb);
        cb.xsetupForScalarSelect();
        cb.getSqlClause().disableSelectIndex(); // for when you use union
        final HpSLSExecutor<CB, RESULT> executor = createHpSLSExecutor(); // variable to resolve generic
        return createSLSFunction(cb, tp, executor);
    }

    @Override
    protected <RESULT> HpSLSFunction<? extends ConditionBean, RESULT> doReadScalar(
            final Class<RESULT> tp) {
        return facadeScalarSelect(tp);
    }

    // ===================================================================================
    //                                                                            Sequence
    //                                                                            ========
    @Override
    protected Number doReadNextVal() {
        final String msg = "This table is NOT related to sequence: "
                + getTableDbName();
        throw new UnsupportedOperationException(msg);
    }

    // ===================================================================================
    //                                                                       Load Referrer
    //                                                                       =============
    /**
     * Load referrer by the the referrer loader. <br />
     * <pre>
     * MemberCB cb = new MemberCB();
     * cb.query().set...
     * List&lt;Member&gt; memberList = memberBhv.selectList(cb);
     * memberBhv.<span style="color: #DD4747">load</span>(memberList, loader -&gt; {
     *     loader.<span style="color: #DD4747">loadPurchaseList</span>(purchaseCB -&gt; {
     *         purchaseCB.query().set...
     *         purchaseCB.query().addOrderBy_PurchasePrice_Desc();
     *     }); <span style="color: #3F7E5E">// you can also load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedList(purchaseLoader -&gt {</span>
     *     <span style="color: #3F7E5E">//    purchaseLoader.loadPurchasePaymentList(...);</span>
     *     <span style="color: #3F7E5E">//});</span>
     *
     *     <span style="color: #3F7E5E">// you can also pull out foreign table and load its referrer</span>
     *     <span style="color: #3F7E5E">// (setupSelect of the foreign table should be called)</span>
     *     <span style="color: #3F7E5E">//loader.pulloutMemberStatus().loadMemberLoginList(...)</span>
     * }
     * for (Member member : memberList) {
     *     List&lt;Purchase&gt; purchaseList = member.<span style="color: #DD4747">getPurchaseList()</span>;
     *     for (Purchase purchase : purchaseList) {
     *         ...
     *     }
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br />
     * The condition-bean, which the set-upper provides, has order by FK before callback.
     * @param requestHeaderList The entity list of requestHeader. (NotNull)
     * @param handler The callback to handle the referrer loader for actually loading referrer. (NotNull)
     */
    public void load(final List<RequestHeader> requestHeaderList,
            final ReferrerLoaderHandler<LoaderOfRequestHeader> handler) {
        xassLRArg(requestHeaderList, handler);
        handler.handle(new LoaderOfRequestHeader().ready(requestHeaderList,
                _behaviorSelector));
    }

    /**
     * Load referrer of ${referrer.referrerJavaBeansRulePropertyName} by the referrer loader. <br />
     * <pre>
     * MemberCB cb = new MemberCB();
     * cb.query().set...
     * Member member = memberBhv.selectEntityWithDeletedCheck(cb);
     * memberBhv.<span style="color: #DD4747">load</span>(member, loader -&gt; {
     *     loader.<span style="color: #DD4747">loadPurchaseList</span>(purchaseCB -&gt; {
     *         purchaseCB.query().set...
     *         purchaseCB.query().addOrderBy_PurchasePrice_Desc();
     *     }); <span style="color: #3F7E5E">// you can also load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedList(purchaseLoader -&gt {</span>
     *     <span style="color: #3F7E5E">//    purchaseLoader.loadPurchasePaymentList(...);</span>
     *     <span style="color: #3F7E5E">//});</span>
     *
     *     <span style="color: #3F7E5E">// you can also pull out foreign table and load its referrer</span>
     *     <span style="color: #3F7E5E">// (setupSelect of the foreign table should be called)</span>
     *     <span style="color: #3F7E5E">//loader.pulloutMemberStatus().loadMemberLoginList(...)</span>
     * }
     * for (Member member : memberList) {
     *     List&lt;Purchase&gt; purchaseList = member.<span style="color: #DD4747">getPurchaseList()</span>;
     *     for (Purchase purchase : purchaseList) {
     *         ...
     *     }
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br />
     * The condition-bean, which the set-upper provides, has order by FK before callback.
     * @param requestHeader The entity of requestHeader. (NotNull)
     * @param handler The callback to handle the referrer loader for actually loading referrer. (NotNull)
     */
    public void load(final RequestHeader requestHeader,
            final ReferrerLoaderHandler<LoaderOfRequestHeader> handler) {
        xassLRArg(requestHeader, handler);
        handler.handle(new LoaderOfRequestHeader().ready(
                xnewLRAryLs(requestHeader), _behaviorSelector));
    }

    // ===================================================================================
    //                                                                   Pull out Relation
    //                                                                   =================
    /**
     * Pull out the list of foreign table 'WebCrawlingConfig'.
     * @param requestHeaderList The list of requestHeader. (NotNull, EmptyAllowed)
     * @return The list of foreign table. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<WebCrawlingConfig> pulloutWebCrawlingConfig(
            final List<RequestHeader> requestHeaderList) {
        return helpPulloutInternally(requestHeaderList, "webCrawlingConfig");
    }

    // ===================================================================================
    //                                                                      Extract Column
    //                                                                      ==============
    /**
     * Extract the value list of (single) primary key id.
     * @param requestHeaderList The list of requestHeader. (NotNull, EmptyAllowed)
     * @return The list of the column value. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<Long> extractIdList(final List<RequestHeader> requestHeaderList) {
        return helpExtractListInternally(requestHeaderList, "id");
    }

    // ===================================================================================
    //                                                                       Entity Update
    //                                                                       =============
    /**
     * Insert the entity modified-only. (DefaultConstraintsEnabled)
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * requestHeader.setFoo...(value);
     * requestHeader.setBar...(value);
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//requestHeader.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//requestHeader.set...;</span>
     * requestHeaderBhv.<span style="color: #DD4747">insert</span>(requestHeader);
     * ... = requestHeader.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * <p>While, when the entity is created by select, all columns are registered.</p>
     * @param requestHeader The entity of insert. (NotNull, PrimaryKeyNullAllowed: when auto-increment)
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void insert(final RequestHeader requestHeader) {
        doInsert(requestHeader, null);
    }

    protected void doInsert(final RequestHeader et,
            final InsertOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeader", et);
        prepareInsertOption(op);
        delegateInsert(et, op);
    }

    protected void prepareInsertOption(final InsertOption<RequestHeaderCB> op) {
        if (op == null) {
            return;
        }
        assertInsertOptionStatus(op);
        if (op.hasSpecifiedInsertColumn()) {
            op.resolveInsertColumnSpecification(createCBForSpecifiedUpdate());
        }
    }

    @Override
    protected void doCreate(final Entity et,
            final InsertOption<? extends ConditionBean> op) {
        doInsert(downcast(et), downcast(op));
    }

    /**
     * Update the entity modified-only. (ZeroUpdateException, ExclusiveControl)
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * requestHeader.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * requestHeader.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//requestHeader.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//requestHeader.set...;</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * requestHeader.<span style="color: #DD4747">setVersionNo</span>(value);
     * try {
     *     requestHeaderBhv.<span style="color: #DD4747">update</span>(requestHeader);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param requestHeader The entity of update. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @exception EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void update(final RequestHeader requestHeader) {
        doUpdate(requestHeader, null);
    }

    protected void doUpdate(final RequestHeader et,
            final UpdateOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeader", et);
        prepareUpdateOption(op);
        helpUpdateInternally(et, op);
    }

    protected void prepareUpdateOption(final UpdateOption<RequestHeaderCB> op) {
        if (op == null) {
            return;
        }
        assertUpdateOptionStatus(op);
        if (op.hasSelfSpecification()) {
            op.resolveSelfSpecification(createCBForVaryingUpdate());
        }
        if (op.hasSpecifiedUpdateColumn()) {
            op.resolveUpdateColumnSpecification(createCBForSpecifiedUpdate());
        }
    }

    protected RequestHeaderCB createCBForVaryingUpdate() {
        final RequestHeaderCB cb = newConditionBean();
        cb.xsetupForVaryingUpdate();
        return cb;
    }

    protected RequestHeaderCB createCBForSpecifiedUpdate() {
        final RequestHeaderCB cb = newConditionBean();
        cb.xsetupForSpecifiedUpdate();
        return cb;
    }

    @Override
    protected void doModify(final Entity et,
            final UpdateOption<? extends ConditionBean> op) {
        doUpdate(downcast(et), downcast(op));
    }

    /**
     * Update the entity non-strictly modified-only. (ZeroUpdateException, NonExclusiveControl)
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * requestHeader.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * requestHeader.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//requestHeader.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//requestHeader.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//requestHeader.setVersionNo(value);</span>
     * requestHeaderBhv.<span style="color: #DD4747">updateNonstrict</span>(requestHeader);
     * </pre>
     * @param requestHeader The entity of update. (NotNull, PrimaryKeyNotNull)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void updateNonstrict(final RequestHeader requestHeader) {
        doUpdateNonstrict(requestHeader, null);
    }

    protected void doUpdateNonstrict(final RequestHeader et,
            final UpdateOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeader", et);
        prepareUpdateOption(op);
        helpUpdateNonstrictInternally(et, op);
    }

    @Override
    protected void doModifyNonstrict(final Entity et,
            final UpdateOption<? extends ConditionBean> op) {
        doUpdateNonstrict(downcast(et), downcast(op));
    }

    /**
     * Insert or update the entity modified-only. (DefaultConstraintsEnabled, ExclusiveControl) <br />
     * if (the entity has no PK) { insert() } else { update(), but no data, insert() } <br />
     * <p><span style="color: #DD4747; font-size: 120%">Attention, you cannot update by unique keys instead of PK.</span></p>
     * @param requestHeader The entity of insert or update. (NotNull, ...depends on insert or update)
     * @exception EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void insertOrUpdate(final RequestHeader requestHeader) {
        doInsertOrUpdate(requestHeader, null, null);
    }

    protected void doInsertOrUpdate(final RequestHeader et,
            final InsertOption<RequestHeaderCB> iop,
            final UpdateOption<RequestHeaderCB> uop) {
        assertObjectNotNull("requestHeader", et);
        helpInsertOrUpdateInternally(et, iop, uop);
    }

    @Override
    protected void doCreateOrModify(final Entity et,
            final InsertOption<? extends ConditionBean> iop,
            final UpdateOption<? extends ConditionBean> uop) {
        doInsertOrUpdate(downcast(et), downcast(iop), downcast(uop));
    }

    /**
     * Insert or update the entity non-strictly modified-only. (DefaultConstraintsEnabled, NonExclusiveControl) <br />
     * if (the entity has no PK) { insert() } else { update(), but no data, insert() }
     * <p><span style="color: #DD4747; font-size: 120%">Attention, you cannot update by unique keys instead of PK.</span></p>
     * @param requestHeader The entity of insert or update. (NotNull, ...depends on insert or update)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void insertOrUpdateNonstrict(final RequestHeader requestHeader) {
        doInsertOrUpdateNonstrict(requestHeader, null, null);
    }

    protected void doInsertOrUpdateNonstrict(final RequestHeader et,
            final InsertOption<RequestHeaderCB> iop,
            final UpdateOption<RequestHeaderCB> uop) {
        assertObjectNotNull("requestHeader", et);
        helpInsertOrUpdateNonstrictInternally(et, iop, uop);
    }

    @Override
    protected void doCreateOrModifyNonstrict(final Entity et,
            final InsertOption<? extends ConditionBean> iop,
            final UpdateOption<? extends ConditionBean> uop) {
        doInsertOrUpdateNonstrict(downcast(et), downcast(iop), downcast(uop));
    }

    /**
     * Delete the entity. (ZeroUpdateException, ExclusiveControl)
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * requestHeader.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * requestHeader.<span style="color: #DD4747">setVersionNo</span>(value);
     * try {
     *     requestHeaderBhv.<span style="color: #DD4747">delete</span>(requestHeader);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param requestHeader The entity of delete. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @exception EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception EntityDuplicatedException When the entity has been duplicated.
     */
    public void delete(final RequestHeader requestHeader) {
        doDelete(requestHeader, null);
    }

    protected void doDelete(final RequestHeader et,
            final DeleteOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeader", et);
        prepareDeleteOption(op);
        helpDeleteInternally(et, op);
    }

    protected void prepareDeleteOption(final DeleteOption<RequestHeaderCB> op) {
        if (op != null) {
            assertDeleteOptionStatus(op);
        }
    }

    @Override
    protected void doRemove(final Entity et,
            final DeleteOption<? extends ConditionBean> op) {
        doDelete(downcast(et), downcast(op));
    }

    /**
     * Delete the entity non-strictly. {ZeroUpdateException, NonExclusiveControl}
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * requestHeader.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//requestHeader.setVersionNo(value);</span>
     * requestHeaderBhv.<span style="color: #DD4747">deleteNonstrict</span>(requestHeader);
     * </pre>
     * @param requestHeader The entity of delete. (NotNull, PrimaryKeyNotNull)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     */
    public void deleteNonstrict(final RequestHeader requestHeader) {
        doDeleteNonstrict(requestHeader, null);
    }

    protected void doDeleteNonstrict(final RequestHeader et,
            final DeleteOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeader", et);
        prepareDeleteOption(op);
        helpDeleteNonstrictInternally(et, op);
    }

    /**
     * Delete the entity non-strictly ignoring deleted. {ZeroUpdateException, NonExclusiveControl}
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * requestHeader.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//requestHeader.setVersionNo(value);</span>
     * requestHeaderBhv.<span style="color: #DD4747">deleteNonstrictIgnoreDeleted</span>(requestHeader);
     * <span style="color: #3F7E5E">// if the target entity doesn't exist, no exception</span>
     * </pre>
     * @param requestHeader The entity of delete. (NotNull, PrimaryKeyNotNull)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     */
    public void deleteNonstrictIgnoreDeleted(final RequestHeader requestHeader) {
        doDeleteNonstrictIgnoreDeleted(requestHeader, null);
    }

    protected void doDeleteNonstrictIgnoreDeleted(final RequestHeader et,
            final DeleteOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeader", et);
        prepareDeleteOption(op);
        helpDeleteNonstrictIgnoreDeletedInternally(et, op);
    }

    @Override
    protected void doRemoveNonstrict(final Entity et,
            final DeleteOption<? extends ConditionBean> op) {
        doDeleteNonstrict(downcast(et), downcast(op));
    }

    // ===================================================================================
    //                                                                        Batch Update
    //                                                                        ============
    /**
     * Batch-insert the entity list modified-only of same-set columns. (DefaultConstraintsEnabled) <br />
     * This method uses executeBatch() of java.sql.PreparedStatement. <br />
     * <p><span style="color: #DD4747; font-size: 120%">The columns of least common multiple are registered like this:</span></p>
     * <pre>
     * for (... : ...) {
     *     RequestHeader requestHeader = new RequestHeader();
     *     requestHeader.setFooName("foo");
     *     if (...) {
     *         requestHeader.setFooPrice(123);
     *     }
     *     <span style="color: #3F7E5E">// FOO_NAME and FOO_PRICE (and record meta columns) are registered</span>
     *     <span style="color: #3F7E5E">// FOO_PRICE not-called in any entities are registered as null without default value</span>
     *     <span style="color: #3F7E5E">// columns not-called in all entities are registered as null or default value</span>
     *     requestHeaderList.add(requestHeader);
     * }
     * requestHeaderBhv.<span style="color: #DD4747">batchInsert</span>(requestHeaderList);
     * </pre>
     * <p>While, when the entities are created by select, all columns are registered.</p>
     * <p>And if the table has an identity, entities after the process don't have incremented values.
     * (When you use the (normal) insert(), you can get the incremented value from your entity)</p>
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNullAllowed: when auto-increment)
     * @return The array of inserted count. (NotNull, EmptyAllowed)
     */
    public int[] batchInsert(final List<RequestHeader> requestHeaderList) {
        return doBatchInsert(requestHeaderList, null);
    }

    protected int[] doBatchInsert(final List<RequestHeader> ls,
            final InsertOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeaderList", ls);
        InsertOption<RequestHeaderCB> rlop;
        if (op != null) {
            rlop = op;
        } else {
            rlop = createPlainInsertOption();
        }
        prepareBatchInsertOption(ls, rlop); // required
        return delegateBatchInsert(ls, rlop);
    }

    protected void prepareBatchInsertOption(final List<RequestHeader> ls,
            final InsertOption<RequestHeaderCB> op) {
        op.xallowInsertColumnModifiedPropertiesFragmented();
        op.xacceptInsertColumnModifiedPropertiesIfNeeds(ls);
        prepareInsertOption(op);
    }

    @Override
    protected int[] doLumpCreate(final List<Entity> ls,
            final InsertOption<? extends ConditionBean> op) {
        return doBatchInsert(downcast(ls), downcast(op));
    }

    /**
     * Batch-update the entity list modified-only of same-set columns. (ExclusiveControl) <br />
     * This method uses executeBatch() of java.sql.PreparedStatement. <br />
     * <span style="color: #DD4747; font-size: 120%">You should specify same-set columns to all entities like this:</span>
     * <pre>
     * for (... : ...) {
     *     RequestHeader requestHeader = new RequestHeader();
     *     requestHeader.setFooName("foo");
     *     if (...) {
     *         requestHeader.setFooPrice(123);
     *     } else {
     *         requestHeader.setFooPrice(null); <span style="color: #3F7E5E">// updated as null</span>
     *         <span style="color: #3F7E5E">//requestHeader.setFooDate(...); // *not allowed, fragmented</span>
     *     }
     *     <span style="color: #3F7E5E">// FOO_NAME and FOO_PRICE (and record meta columns) are updated</span>
     *     <span style="color: #3F7E5E">// (others are not updated: their values are kept)</span>
     *     requestHeaderList.add(requestHeader);
     * }
     * requestHeaderBhv.<span style="color: #DD4747">batchUpdate</span>(requestHeaderList);
     * </pre>
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @exception BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchUpdate(final List<RequestHeader> requestHeaderList) {
        return doBatchUpdate(requestHeaderList, null);
    }

    protected int[] doBatchUpdate(final List<RequestHeader> ls,
            final UpdateOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeaderList", ls);
        UpdateOption<RequestHeaderCB> rlop;
        if (op != null) {
            rlop = op;
        } else {
            rlop = createPlainUpdateOption();
        }
        prepareBatchUpdateOption(ls, rlop); // required
        return delegateBatchUpdate(ls, rlop);
    }

    protected void prepareBatchUpdateOption(final List<RequestHeader> ls,
            final UpdateOption<RequestHeaderCB> op) {
        op.xacceptUpdateColumnModifiedPropertiesIfNeeds(ls);
        prepareUpdateOption(op);
    }

    @Override
    protected int[] doLumpModify(final List<Entity> ls,
            final UpdateOption<? extends ConditionBean> op) {
        return doBatchUpdate(downcast(ls), downcast(op));
    }

    /**
     * Batch-update the entity list specified-only. (ExclusiveControl) <br />
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * <pre>
     * <span style="color: #3F7E5E">// e.g. update two columns only</span>
     * requestHeaderBhv.<span style="color: #DD4747">batchUpdate</span>(requestHeaderList, new SpecifyQuery<RequestHeaderCB>() {
     *     public void specify(RequestHeaderCB cb) { <span style="color: #3F7E5E">// the two only updated</span>
     *         cb.specify().<span style="color: #DD4747">columnFooStatusCode()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     *         cb.specify().<span style="color: #DD4747">columnBarDate()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     *     }
     * });
     * <span style="color: #3F7E5E">// e.g. update every column in the table</span>
     * requestHeaderBhv.<span style="color: #DD4747">batchUpdate</span>(requestHeaderList, new SpecifyQuery<RequestHeaderCB>() {
     *     public void specify(RequestHeaderCB cb) { <span style="color: #3F7E5E">// all columns are updated</span>
     *         cb.specify().<span style="color: #DD4747">columnEveryColumn()</span>; <span style="color: #3F7E5E">// no check of modified properties</span>
     *     }
     * });
     * </pre>
     * <p>You can specify update columns used on set clause of update statement.
     * However you do not need to specify common columns for update
     * and an optimistic lock column because they are specified implicitly.</p>
     * <p>And you should specify columns that are modified in any entities (at least one entity).
     * But if you specify every column, it has no check.</p>
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param updateColumnSpec The specification of update columns. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @exception BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchUpdate(final List<RequestHeader> requestHeaderList,
            final SpecifyQuery<RequestHeaderCB> updateColumnSpec) {
        return doBatchUpdate(requestHeaderList,
                createSpecifiedUpdateOption(updateColumnSpec));
    }

    /**
     * Batch-update the entity list non-strictly modified-only of same-set columns. (NonExclusiveControl) <br />
     * This method uses executeBatch() of java.sql.PreparedStatement. <br />
     * <span style="color: #DD4747; font-size: 140%">You should specify same-set columns to all entities like this:</span>
     * <pre>
     * for (... : ...) {
     *     RequestHeader requestHeader = new RequestHeader();
     *     requestHeader.setFooName("foo");
     *     if (...) {
     *         requestHeader.setFooPrice(123);
     *     } else {
     *         requestHeader.setFooPrice(null); <span style="color: #3F7E5E">// updated as null</span>
     *         <span style="color: #3F7E5E">//requestHeader.setFooDate(...); // *not allowed, fragmented</span>
     *     }
     *     <span style="color: #3F7E5E">// FOO_NAME and FOO_PRICE (and record meta columns) are updated</span>
     *     <span style="color: #3F7E5E">// (others are not updated: their values are kept)</span>
     *     requestHeaderList.add(requestHeader);
     * }
     * requestHeaderBhv.<span style="color: #DD4747">batchUpdate</span>(requestHeaderList);
     * </pre>
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchUpdateNonstrict(
            final List<RequestHeader> requestHeaderList) {
        return doBatchUpdateNonstrict(requestHeaderList, null);
    }

    protected int[] doBatchUpdateNonstrict(final List<RequestHeader> ls,
            final UpdateOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeaderList", ls);
        UpdateOption<RequestHeaderCB> rlop;
        if (op != null) {
            rlop = op;
        } else {
            rlop = createPlainUpdateOption();
        }
        prepareBatchUpdateOption(ls, rlop);
        return delegateBatchUpdateNonstrict(ls, rlop);
    }

    /**
     * Batch-update the entity list non-strictly specified-only. (NonExclusiveControl) <br />
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * <pre>
     * <span style="color: #3F7E5E">// e.g. update two columns only</span>
     * requestHeaderBhv.<span style="color: #DD4747">batchUpdateNonstrict</span>(requestHeaderList, new SpecifyQuery<RequestHeaderCB>() {
     *     public void specify(RequestHeaderCB cb) { <span style="color: #3F7E5E">// the two only updated</span>
     *         cb.specify().<span style="color: #DD4747">columnFooStatusCode()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     *         cb.specify().<span style="color: #DD4747">columnBarDate()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     *     }
     * });
     * <span style="color: #3F7E5E">// e.g. update every column in the table</span>
     * requestHeaderBhv.<span style="color: #DD4747">batchUpdateNonstrict</span>(requestHeaderList, new SpecifyQuery<RequestHeaderCB>() {
     *     public void specify(RequestHeaderCB cb) { <span style="color: #3F7E5E">// all columns are updated</span>
     *         cb.specify().<span style="color: #DD4747">columnEveryColumn()</span>; <span style="color: #3F7E5E">// no check of modified properties</span>
     *     }
     * });
     * </pre>
     * <p>You can specify update columns used on set clause of update statement.
     * However you do not need to specify common columns for update
     * and an optimistic lock column because they are specified implicitly.</p>
     * <p>And you should specify columns that are modified in any entities (at least one entity).</p>
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param updateColumnSpec The specification of update columns. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchUpdateNonstrict(
            final List<RequestHeader> requestHeaderList,
            final SpecifyQuery<RequestHeaderCB> updateColumnSpec) {
        return doBatchUpdateNonstrict(requestHeaderList,
                createSpecifiedUpdateOption(updateColumnSpec));
    }

    @Override
    protected int[] doLumpModifyNonstrict(final List<Entity> ls,
            final UpdateOption<? extends ConditionBean> op) {
        return doBatchUpdateNonstrict(downcast(ls), downcast(op));
    }

    /**
     * Batch-delete the entity list. (ExclusiveControl) <br />
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     * @exception BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchDelete(final List<RequestHeader> requestHeaderList) {
        return doBatchDelete(requestHeaderList, null);
    }

    protected int[] doBatchDelete(final List<RequestHeader> ls,
            final DeleteOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeaderList", ls);
        prepareDeleteOption(op);
        return delegateBatchDelete(ls, op);
    }

    @Override
    protected int[] doLumpRemove(final List<Entity> ls,
            final DeleteOption<? extends ConditionBean> op) {
        return doBatchDelete(downcast(ls), downcast(op));
    }

    /**
     * Batch-delete the entity list non-strictly. {NonExclusiveControl} <br />
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchDeleteNonstrict(
            final List<RequestHeader> requestHeaderList) {
        return doBatchDeleteNonstrict(requestHeaderList, null);
    }

    protected int[] doBatchDeleteNonstrict(final List<RequestHeader> ls,
            final DeleteOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeaderList", ls);
        prepareDeleteOption(op);
        return delegateBatchDeleteNonstrict(ls, op);
    }

    @Override
    protected int[] doLumpRemoveNonstrict(final List<Entity> ls,
            final DeleteOption<? extends ConditionBean> op) {
        return doBatchDeleteNonstrict(downcast(ls), downcast(op));
    }

    // ===================================================================================
    //                                                                        Query Update
    //                                                                        ============
    /**
     * Insert the several entities by query (modified-only for fixed value).
     * <pre>
     * requestHeaderBhv.<span style="color: #DD4747">queryInsert</span>(new QueryInsertSetupper&lt;RequestHeader, RequestHeaderCB&gt;() {
     *     public ConditionBean setup(requestHeader entity, RequestHeaderCB intoCB) {
     *         FooCB cb = FooCB();
     *         cb.setupSelect_Bar();
     *
     *         <span style="color: #3F7E5E">// mapping</span>
     *         intoCB.specify().columnMyName().mappedFrom(cb.specify().columnFooName());
     *         intoCB.specify().columnMyCount().mappedFrom(cb.specify().columnFooCount());
     *         intoCB.specify().columnMyDate().mappedFrom(cb.specify().specifyBar().columnBarDate());
     *         entity.setMyFixedValue("foo"); <span style="color: #3F7E5E">// fixed value</span>
     *         <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     *         <span style="color: #3F7E5E">//entity.setRegisterUser(value);</span>
     *         <span style="color: #3F7E5E">//entity.set...;</span>
     *         <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     *         <span style="color: #3F7E5E">//entity.setVersionNo(value);</span>
     *
     *         return cb;
     *     }
     * });
     * </pre>
     * @param setupper The setup-per of query-insert. (NotNull)
     * @return The inserted count.
     */
    public int queryInsert(
            final QueryInsertSetupper<RequestHeader, RequestHeaderCB> setupper) {
        return doQueryInsert(setupper, null);
    }

    protected int doQueryInsert(
            final QueryInsertSetupper<RequestHeader, RequestHeaderCB> sp,
            final InsertOption<RequestHeaderCB> op) {
        assertObjectNotNull("setupper", sp);
        prepareInsertOption(op);
        final RequestHeader et = newEntity();
        final RequestHeaderCB cb = createCBForQueryInsert();
        return delegateQueryInsert(et, cb, sp.setup(et, cb), op);
    }

    protected RequestHeaderCB createCBForQueryInsert() {
        final RequestHeaderCB cb = newConditionBean();
        cb.xsetupForQueryInsert();
        return cb;
    }

    @Override
    protected int doRangeCreate(
            final QueryInsertSetupper<? extends Entity, ? extends ConditionBean> setupper,
            final InsertOption<? extends ConditionBean> op) {
        return doQueryInsert(downcast(setupper), downcast(op));
    }

    /**
     * Update the several entities by query non-strictly modified-only. (NonExclusiveControl)
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//requestHeader.setPK...(value);</span>
     * requestHeader.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//requestHeader.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//requestHeader.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//requestHeader.setVersionNo(value);</span>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * requestHeaderBhv.<span style="color: #DD4747">queryUpdate</span>(requestHeader, cb);
     * </pre>
     * @param requestHeader The entity that contains update values. (NotNull, PrimaryKeyNullAllowed)
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @return The updated count.
     * @exception NonQueryUpdateNotAllowedException When the query has no condition.
     */
    public int queryUpdate(final RequestHeader requestHeader,
            final RequestHeaderCB cb) {
        return doQueryUpdate(requestHeader, cb, null);
    }

    protected int doQueryUpdate(final RequestHeader et,
            final RequestHeaderCB cb, final UpdateOption<RequestHeaderCB> op) {
        assertObjectNotNull("requestHeader", et);
        assertCBStateValid(cb);
        prepareUpdateOption(op);
        return checkCountBeforeQueryUpdateIfNeeds(cb) ? delegateQueryUpdate(et,
                cb, op) : 0;
    }

    @Override
    protected int doRangeModify(final Entity et, final ConditionBean cb,
            final UpdateOption<? extends ConditionBean> op) {
        return doQueryUpdate(downcast(et), downcast(cb), downcast(op));
    }

    /**
     * Delete the several entities by query. (NonExclusiveControl)
     * <pre>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * requestHeaderBhv.<span style="color: #DD4747">queryDelete</span>(requestHeader, cb);
     * </pre>
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @return The deleted count.
     * @exception NonQueryDeleteNotAllowedException When the query has no condition.
     */
    public int queryDelete(final RequestHeaderCB cb) {
        return doQueryDelete(cb, null);
    }

    protected int doQueryDelete(final RequestHeaderCB cb,
            final DeleteOption<RequestHeaderCB> op) {
        assertCBStateValid(cb);
        prepareDeleteOption(op);
        return checkCountBeforeQueryUpdateIfNeeds(cb) ? delegateQueryDelete(cb,
                op) : 0;
    }

    @Override
    protected int doRangeRemove(final ConditionBean cb,
            final DeleteOption<? extends ConditionBean> op) {
        return doQueryDelete(downcast(cb), downcast(op));
    }

    // ===================================================================================
    //                                                                      Varying Update
    //                                                                      ==============
    // -----------------------------------------------------
    //                                         Entity Update
    //                                         -------------
    /**
     * Insert the entity with varying requests. <br />
     * For example, disableCommonColumnAutoSetup(), disablePrimaryKeyIdentity(). <br />
     * Other specifications are same as insert(entity).
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * requestHeader.setFoo...(value);
     * requestHeader.setBar...(value);
     * InsertOption<RequestHeaderCB> option = new InsertOption<RequestHeaderCB>();
     * <span style="color: #3F7E5E">// you can insert by your values for common columns</span>
     * option.disableCommonColumnAutoSetup();
     * requestHeaderBhv.<span style="color: #DD4747">varyingInsert</span>(requestHeader, option);
     * ... = requestHeader.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * @param requestHeader The entity of insert. (NotNull, PrimaryKeyNullAllowed: when auto-increment)
     * @param option The option of insert for varying requests. (NotNull)
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingInsert(final RequestHeader requestHeader,
            final InsertOption<RequestHeaderCB> option) {
        assertInsertOptionNotNull(option);
        doInsert(requestHeader, option);
    }

    /**
     * Update the entity with varying requests modified-only. (ZeroUpdateException, ExclusiveControl) <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br />
     * Other specifications are same as update(entity).
     * <pre>
     * RequestHeader requestHeader = new RequestHeader();
     * requestHeader.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * requestHeader.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * requestHeader.<span style="color: #DD4747">setVersionNo</span>(value);
     * try {
     *     <span style="color: #3F7E5E">// you can update by self calculation values</span>
     *     UpdateOption&lt;RequestHeaderCB&gt; option = new UpdateOption&lt;RequestHeaderCB&gt;();
     *     option.self(new SpecifyQuery&lt;RequestHeaderCB&gt;() {
     *         public void specify(RequestHeaderCB cb) {
     *             cb.specify().<span style="color: #DD4747">columnXxxCount()</span>;
     *         }
     *     }).plus(1); <span style="color: #3F7E5E">// XXX_COUNT = XXX_COUNT + 1</span>
     *     requestHeaderBhv.<span style="color: #DD4747">varyingUpdate</span>(requestHeader, option);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param requestHeader The entity of update. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @exception EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingUpdate(final RequestHeader requestHeader,
            final UpdateOption<RequestHeaderCB> option) {
        assertUpdateOptionNotNull(option);
        doUpdate(requestHeader, option);
    }

    /**
     * Update the entity with varying requests non-strictly modified-only. (ZeroUpdateException, NonExclusiveControl) <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br />
     * Other specifications are same as updateNonstrict(entity).
     * <pre>
     * <span style="color: #3F7E5E">// ex) you can update by self calculation values</span>
     * RequestHeader requestHeader = new RequestHeader();
     * requestHeader.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * requestHeader.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//requestHeader.setVersionNo(value);</span>
     * UpdateOption&lt;RequestHeaderCB&gt; option = new UpdateOption&lt;RequestHeaderCB&gt;();
     * option.self(new SpecifyQuery&lt;RequestHeaderCB&gt;() {
     *     public void specify(RequestHeaderCB cb) {
     *         cb.specify().<span style="color: #DD4747">columnFooCount()</span>;
     *     }
     * }).plus(1); <span style="color: #3F7E5E">// FOO_COUNT = FOO_COUNT + 1</span>
     * requestHeaderBhv.<span style="color: #DD4747">varyingUpdateNonstrict</span>(requestHeader, option);
     * </pre>
     * @param requestHeader The entity of update. (NotNull, PrimaryKeyNotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingUpdateNonstrict(final RequestHeader requestHeader,
            final UpdateOption<RequestHeaderCB> option) {
        assertUpdateOptionNotNull(option);
        doUpdateNonstrict(requestHeader, option);
    }

    /**
     * Insert or update the entity with varying requests. (ExclusiveControl: when update) <br />
     * Other specifications are same as insertOrUpdate(entity).
     * @param requestHeader The entity of insert or update. (NotNull)
     * @param insertOption The option of insert for varying requests. (NotNull)
     * @param updateOption The option of update for varying requests. (NotNull)
     * @exception EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingInsertOrUpdate(final RequestHeader requestHeader,
            final InsertOption<RequestHeaderCB> insertOption,
            final UpdateOption<RequestHeaderCB> updateOption) {
        assertInsertOptionNotNull(insertOption);
        assertUpdateOptionNotNull(updateOption);
        doInsertOrUpdate(requestHeader, insertOption, updateOption);
    }

    /**
     * Insert or update the entity with varying requests non-strictly. (NonExclusiveControl: when update) <br />
     * Other specifications are same as insertOrUpdateNonstrict(entity).
     * @param requestHeader The entity of insert or update. (NotNull)
     * @param insertOption The option of insert for varying requests. (NotNull)
     * @param updateOption The option of update for varying requests. (NotNull)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     * @exception EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingInsertOrUpdateNonstrict(
            final RequestHeader requestHeader,
            final InsertOption<RequestHeaderCB> insertOption,
            final UpdateOption<RequestHeaderCB> updateOption) {
        assertInsertOptionNotNull(insertOption);
        assertUpdateOptionNotNull(updateOption);
        doInsertOrUpdateNonstrict(requestHeader, insertOption, updateOption);
    }

    /**
     * Delete the entity with varying requests. (ZeroUpdateException, ExclusiveControl) <br />
     * Now a valid option does not exist. <br />
     * Other specifications are same as delete(entity).
     * @param requestHeader The entity of delete. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @exception EntityAlreadyUpdatedException When the entity has already been updated.
     * @exception EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDelete(final RequestHeader requestHeader,
            final DeleteOption<RequestHeaderCB> option) {
        assertDeleteOptionNotNull(option);
        doDelete(requestHeader, option);
    }

    /**
     * Delete the entity with varying requests non-strictly. (ZeroUpdateException, NonExclusiveControl) <br />
     * Now a valid option does not exist. <br />
     * Other specifications are same as deleteNonstrict(entity).
     * @param requestHeader The entity of delete. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @exception EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @exception EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDeleteNonstrict(final RequestHeader requestHeader,
            final DeleteOption<RequestHeaderCB> option) {
        assertDeleteOptionNotNull(option);
        doDeleteNonstrict(requestHeader, option);
    }

    // -----------------------------------------------------
    //                                          Batch Update
    //                                          ------------
    /**
     * Batch-insert the list with varying requests. <br />
     * For example, disableCommonColumnAutoSetup()
     * , disablePrimaryKeyIdentity(), limitBatchInsertLogging(). <br />
     * Other specifications are same as batchInsert(entityList).
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param option The option of insert for varying requests. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchInsert(
            final List<RequestHeader> requestHeaderList,
            final InsertOption<RequestHeaderCB> option) {
        assertInsertOptionNotNull(option);
        return doBatchInsert(requestHeaderList, option);
    }

    /**
     * Batch-update the list with varying requests. <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br />
     * Other specifications are same as batchUpdate(entityList).
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchUpdate(
            final List<RequestHeader> requestHeaderList,
            final UpdateOption<RequestHeaderCB> option) {
        assertUpdateOptionNotNull(option);
        return doBatchUpdate(requestHeaderList, option);
    }

    /**
     * Batch-update the list with varying requests non-strictly. <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br />
     * Other specifications are same as batchUpdateNonstrict(entityList).
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchUpdateNonstrict(
            final List<RequestHeader> requestHeaderList,
            final UpdateOption<RequestHeaderCB> option) {
        assertUpdateOptionNotNull(option);
        return doBatchUpdateNonstrict(requestHeaderList, option);
    }

    /**
     * Batch-delete the list with varying requests. <br />
     * For example, limitBatchDeleteLogging(). <br />
     * Other specifications are same as batchDelete(entityList).
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchDelete(
            final List<RequestHeader> requestHeaderList,
            final DeleteOption<RequestHeaderCB> option) {
        assertDeleteOptionNotNull(option);
        return doBatchDelete(requestHeaderList, option);
    }

    /**
     * Batch-delete the list with varying requests non-strictly. <br />
     * For example, limitBatchDeleteLogging(). <br />
     * Other specifications are same as batchDeleteNonstrict(entityList).
     * @param requestHeaderList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchDeleteNonstrict(
            final List<RequestHeader> requestHeaderList,
            final DeleteOption<RequestHeaderCB> option) {
        assertDeleteOptionNotNull(option);
        return doBatchDeleteNonstrict(requestHeaderList, option);
    }

    // -----------------------------------------------------
    //                                          Query Update
    //                                          ------------
    /**
     * Insert the several entities by query with varying requests (modified-only for fixed value). <br />
     * For example, disableCommonColumnAutoSetup(), disablePrimaryKeyIdentity(). <br />
     * Other specifications are same as queryInsert(entity, setupper).
     * @param setupper The setup-per of query-insert. (NotNull)
     * @param option The option of insert for varying requests. (NotNull)
     * @return The inserted count.
     */
    public int varyingQueryInsert(
            final QueryInsertSetupper<RequestHeader, RequestHeaderCB> setupper,
            final InsertOption<RequestHeaderCB> option) {
        assertInsertOptionNotNull(option);
        return doQueryInsert(setupper, option);
    }

    /**
     * Update the several entities by query with varying requests non-strictly modified-only. {NonExclusiveControl} <br />
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), allowNonQueryUpdate(). <br />
     * Other specifications are same as queryUpdate(entity, cb).
     * <pre>
     * <span style="color: #3F7E5E">// ex) you can update by self calculation values</span>
     * RequestHeader requestHeader = new RequestHeader();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//requestHeader.setPK...(value);</span>
     * requestHeader.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//requestHeader.setVersionNo(value);</span>
     * RequestHeaderCB cb = new RequestHeaderCB();
     * cb.query().setFoo...(value);
     * UpdateOption&lt;RequestHeaderCB&gt; option = new UpdateOption&lt;RequestHeaderCB&gt;();
     * option.self(new SpecifyQuery&lt;RequestHeaderCB&gt;() {
     *     public void specify(RequestHeaderCB cb) {
     *         cb.specify().<span style="color: #DD4747">columnFooCount()</span>;
     *     }
     * }).plus(1); <span style="color: #3F7E5E">// FOO_COUNT = FOO_COUNT + 1</span>
     * requestHeaderBhv.<span style="color: #DD4747">varyingQueryUpdate</span>(requestHeader, cb, option);
     * </pre>
     * @param requestHeader The entity that contains update values. (NotNull) {PrimaryKeyNotRequired}
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @param option The option of update for varying requests. (NotNull)
     * @return The updated count.
     * @exception NonQueryUpdateNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryUpdate(final RequestHeader requestHeader,
            final RequestHeaderCB cb, final UpdateOption<RequestHeaderCB> option) {
        assertUpdateOptionNotNull(option);
        return doQueryUpdate(requestHeader, cb, option);
    }

    /**
     * Delete the several entities by query with varying requests non-strictly. <br />
     * For example, allowNonQueryDelete(). <br />
     * Other specifications are same as batchUpdateNonstrict(entityList).
     * @param cb The condition-bean of RequestHeader. (NotNull)
     * @param option The option of delete for varying requests. (NotNull)
     * @return The deleted count.
     * @exception NonQueryDeleteNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryDelete(final RequestHeaderCB cb,
            final DeleteOption<RequestHeaderCB> option) {
        assertDeleteOptionNotNull(option);
        return doQueryDelete(cb, option);
    }

    // ===================================================================================
    //                                                                          OutsideSql
    //                                                                          ==========
    /**
     * Prepare the basic executor of outside-SQL to execute it. <br />
     * The invoker of behavior command should be not null when you call this method.
     * <pre>
     * You can use the methods for outside-SQL are as follows:
     * {Basic}
     *   o selectList()
     *   o execute()
     *   o call()
     *
     * {Entity}
     *   o entityHandling().selectEntity()
     *   o entityHandling().selectEntityWithDeletedCheck()
     *
     * {Paging}
     *   o autoPaging().selectList()
     *   o autoPaging().selectPage()
     *   o manualPaging().selectList()
     *   o manualPaging().selectPage()
     *
     * {Cursor}
     *   o cursorHandling().selectCursor()
     *
     * {Option}
     *   o dynamicBinding().selectList()
     *   o removeBlockComment().selectList()
     *   o removeLineComment().selectList()
     *   o formatSql().selectList()
     * </pre>
     * @return The basic executor of outside-SQL. (NotNull)
     */
    public OutsideSqlBasicExecutor<RequestHeaderBhv> outsideSql() {
        return doOutsideSql();
    }

    // ===================================================================================
    //                                                                Optimistic Lock Info
    //                                                                ====================
    @Override
    protected boolean hasVersionNoValue(final Entity et) {
        return downcast(et).getVersionNo() != null;
    }

    // ===================================================================================
    //                                                                       Assist Helper
    //                                                                       =============
    protected Class<RequestHeader> typeOfSelectedEntity() {
        return RequestHeader.class;
    }

    protected RequestHeader downcast(final Entity et) {
        return helpEntityDowncastInternally(et, RequestHeader.class);
    }

    protected RequestHeaderCB downcast(final ConditionBean cb) {
        return helpConditionBeanDowncastInternally(cb, RequestHeaderCB.class);
    }

    @SuppressWarnings("unchecked")
    protected List<RequestHeader> downcast(final List<? extends Entity> ls) {
        return (List<RequestHeader>) ls;
    }

    @SuppressWarnings("unchecked")
    protected InsertOption<RequestHeaderCB> downcast(
            final InsertOption<? extends ConditionBean> op) {
        return (InsertOption<RequestHeaderCB>) op;
    }

    @SuppressWarnings("unchecked")
    protected UpdateOption<RequestHeaderCB> downcast(
            final UpdateOption<? extends ConditionBean> op) {
        return (UpdateOption<RequestHeaderCB>) op;
    }

    @SuppressWarnings("unchecked")
    protected DeleteOption<RequestHeaderCB> downcast(
            final DeleteOption<? extends ConditionBean> op) {
        return (DeleteOption<RequestHeaderCB>) op;
    }

    @SuppressWarnings("unchecked")
    protected QueryInsertSetupper<RequestHeader, RequestHeaderCB> downcast(
            final QueryInsertSetupper<? extends Entity, ? extends ConditionBean> sp) {
        return (QueryInsertSetupper<RequestHeader, RequestHeaderCB>) sp;
    }
}
