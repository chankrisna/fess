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

package jp.sf.fess.db.bsentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jp.sf.fess.db.allcommon.DBMetaInstanceHandler;
import jp.sf.fess.db.exentity.LabelType;
import jp.sf.fess.db.exentity.WebConfigToLabelTypeMapping;
import jp.sf.fess.db.exentity.WebCrawlingConfig;

import org.seasar.dbflute.Entity;
import org.seasar.dbflute.dbmeta.DBMeta;

/**
 * The entity of WEB_CONFIG_TO_LABEL_TYPE_MAPPING as TABLE. <br />
 * <pre>
 * [primary-key]
 *     ID
 *
 * [column]
 *     ID, WEB_CONFIG_ID, LABEL_TYPE_ID
 *
 * [sequence]
 *
 *
 * [identity]
 *     ID
 *
 * [version-no]
 *
 *
 * [foreign table]
 *     LABEL_TYPE, WEB_CRAWLING_CONFIG
 *
 * [referrer table]
 *
 *
 * [foreign property]
 *     labelType, webCrawlingConfig
 *
 * [referrer property]
 *
 *
 * [get/set template]
 * /= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 * Long id = entity.getId();
 * Long webConfigId = entity.getWebConfigId();
 * Long labelTypeId = entity.getLabelTypeId();
 * entity.setId(id);
 * entity.setWebConfigId(webConfigId);
 * entity.setLabelTypeId(labelTypeId);
 * = = = = = = = = = =/
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsWebConfigToLabelTypeMapping implements Entity,
        Serializable, Cloneable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** Serial version UID. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // -----------------------------------------------------
    //                                                Column
    //                                                ------
    /** ID: {PK, ID, NotNull, BIGINT(19)} */
    protected Long _id;

    /** WEB_CONFIG_ID: {IX, NotNull, BIGINT(19), FK to WEB_CRAWLING_CONFIG} */
    protected Long _webConfigId;

    /** LABEL_TYPE_ID: {IX, NotNull, BIGINT(19), FK to LABEL_TYPE} */
    protected Long _labelTypeId;

    // -----------------------------------------------------
    //                                              Internal
    //                                              --------
    /** The unique-driven properties for this entity. (NotNull) */
    protected final EntityUniqueDrivenProperties __uniqueDrivenProperties = newUniqueDrivenProperties();

    /** The modified properties for this entity. (NotNull) */
    protected final EntityModifiedProperties __modifiedProperties = newModifiedProperties();

    /** Is the entity created by DBFlute select process? */
    protected boolean __createdBySelect;

    // ===================================================================================
    //                                                                          Table Name
    //                                                                          ==========
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTableDbName() {
        return "WEB_CONFIG_TO_LABEL_TYPE_MAPPING";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTablePropertyName() { // according to Java Beans rule
        return "webConfigToLabelTypeMapping";
    }

    // ===================================================================================
    //                                                                              DBMeta
    //                                                                              ======
    /**
     * {@inheritDoc}
     */
    @Override
    public DBMeta getDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(getTableDbName());
    }

    // ===================================================================================
    //                                                                         Primary Key
    //                                                                         ===========
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPrimaryKeyValue() {
        if (getId() == null) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> myuniqueDrivenProperties() {
        return __uniqueDrivenProperties.getPropertyNames();
    }

    protected EntityUniqueDrivenProperties newUniqueDrivenProperties() {
        return new EntityUniqueDrivenProperties();
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    /** LABEL_TYPE by my LABEL_TYPE_ID, named 'labelType'. */
    protected LabelType _labelType;

    /**
     * [get] LABEL_TYPE by my LABEL_TYPE_ID, named 'labelType'.
     * @return The entity of foreign property 'labelType'. (NullAllowed: when e.g. null FK column, no setupSelect)
     */
    public LabelType getLabelType() {
        return _labelType;
    }

    /**
     * [set] LABEL_TYPE by my LABEL_TYPE_ID, named 'labelType'.
     * @param labelType The entity of foreign property 'labelType'. (NullAllowed)
     */
    public void setLabelType(final LabelType labelType) {
        _labelType = labelType;
    }

    /** WEB_CRAWLING_CONFIG by my WEB_CONFIG_ID, named 'webCrawlingConfig'. */
    protected WebCrawlingConfig _webCrawlingConfig;

    /**
     * [get] WEB_CRAWLING_CONFIG by my WEB_CONFIG_ID, named 'webCrawlingConfig'.
     * @return The entity of foreign property 'webCrawlingConfig'. (NullAllowed: when e.g. null FK column, no setupSelect)
     */
    public WebCrawlingConfig getWebCrawlingConfig() {
        return _webCrawlingConfig;
    }

    /**
     * [set] WEB_CRAWLING_CONFIG by my WEB_CONFIG_ID, named 'webCrawlingConfig'.
     * @param webCrawlingConfig The entity of foreign property 'webCrawlingConfig'. (NullAllowed)
     */
    public void setWebCrawlingConfig(final WebCrawlingConfig webCrawlingConfig) {
        _webCrawlingConfig = webCrawlingConfig;
    }

    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    protected <ELEMENT> List<ELEMENT> newReferrerList() {
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                 Modified Properties
    //                                                                 ===================
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> modifiedProperties() {
        return __modifiedProperties.getPropertyNames();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearModifiedInfo() {
        __modifiedProperties.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasModification() {
        return !__modifiedProperties.isEmpty();
    }

    protected EntityModifiedProperties newModifiedProperties() {
        return new EntityModifiedProperties();
    }

    // ===================================================================================
    //                                                                     Birthplace Mark
    //                                                                     ===============
    /**
     * {@inheritDoc}
     */
    @Override
    public void markAsSelect() {
        __createdBySelect = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createdBySelect() {
        return __createdBySelect;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    /**
     * Determine the object is equal with this. <br />
     * If primary-keys or columns of the other are same as this one, returns true.
     * @param obj The object as other entity. (NullAllowed: if null, returns false fixedly)
     * @return Comparing result.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof BsWebConfigToLabelTypeMapping)) {
            return false;
        }
        final BsWebConfigToLabelTypeMapping other = (BsWebConfigToLabelTypeMapping) obj;
        if (!xSV(getId(), other.getId())) {
            return false;
        }
        return true;
    }

    protected boolean xSV(final Object v1, final Object v2) {
        return FunCustodial.isSameValue(v1, v2);
    }

    /**
     * Calculate the hash-code from primary-keys or columns.
     * @return The hash-code from primary-key or columns.
     */
    @Override
    public int hashCode() {
        int hs = 17;
        hs = xCH(hs, getTableDbName());
        hs = xCH(hs, getId());
        return hs;
    }

    protected int xCH(final int hs, final Object vl) {
        return FunCustodial.calculateHashcode(hs, vl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int instanceHash() {
        return super.hashCode();
    }

    /**
     * Convert to display string of entity's data. (no relation data)
     * @return The display string of all columns and relation existences. (NotNull)
     */
    @Override
    public String toString() {
        return buildDisplayString(FunCustodial.toClassTitle(this), true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStringWithRelation() {
        final StringBuilder sb = new StringBuilder();
        sb.append(toString());
        final String li = "\n  ";
        if (_labelType != null) {
            sb.append(li).append(xbRDS(_labelType, "labelType"));
        }
        if (_webCrawlingConfig != null) {
            sb.append(li)
                    .append(xbRDS(_webCrawlingConfig, "webCrawlingConfig"));
        }
        return sb.toString();
    }

    protected String xbRDS(final Entity et, final String name) { // buildRelationDisplayString()
        return et.buildDisplayString(name, true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildDisplayString(final String name, final boolean column,
            final boolean relation) {
        final StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(name).append(column || relation ? ":" : "");
        }
        if (column) {
            sb.append(buildColumnString());
        }
        if (relation) {
            sb.append(buildRelationString());
        }
        sb.append("@").append(Integer.toHexString(hashCode()));
        return sb.toString();
    }

    protected String buildColumnString() {
        final StringBuilder sb = new StringBuilder();
        final String dm = ", ";
        sb.append(dm).append(getId());
        sb.append(dm).append(getWebConfigId());
        sb.append(dm).append(getLabelTypeId());
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    protected String buildRelationString() {
        final StringBuilder sb = new StringBuilder();
        final String cm = ",";
        if (_labelType != null) {
            sb.append(cm).append("labelType");
        }
        if (_webCrawlingConfig != null) {
            sb.append(cm).append("webCrawlingConfig");
        }
        if (sb.length() > cm.length()) {
            sb.delete(0, cm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    /**
     * Clone entity instance using super.clone(). (shallow copy)
     * @return The cloned instance of this entity. (NotNull)
     */
    @Override
    public WebConfigToLabelTypeMapping clone() {
        try {
            return (WebConfigToLabelTypeMapping) super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new IllegalStateException("Failed to clone the entity: "
                    + toString(), e);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] ID: {PK, ID, NotNull, BIGINT(19)} <br />
     * @return The value of the column 'ID'. (basically NotNull if selected: for the constraint)
     */
    public Long getId() {
        return _id;
    }

    /**
     * [set] ID: {PK, ID, NotNull, BIGINT(19)} <br />
     * @param id The value of the column 'ID'. (basically NotNull if update: for the constraint)
     */
    public void setId(final Long id) {
        __modifiedProperties.addPropertyName("id");
        _id = id;
    }

    /**
     * [get] WEB_CONFIG_ID: {IX, NotNull, BIGINT(19), FK to WEB_CRAWLING_CONFIG} <br />
     * @return The value of the column 'WEB_CONFIG_ID'. (basically NotNull if selected: for the constraint)
     */
    public Long getWebConfigId() {
        return _webConfigId;
    }

    /**
     * [set] WEB_CONFIG_ID: {IX, NotNull, BIGINT(19), FK to WEB_CRAWLING_CONFIG} <br />
     * @param webConfigId The value of the column 'WEB_CONFIG_ID'. (basically NotNull if update: for the constraint)
     */
    public void setWebConfigId(final Long webConfigId) {
        __modifiedProperties.addPropertyName("webConfigId");
        _webConfigId = webConfigId;
    }

    /**
     * [get] LABEL_TYPE_ID: {IX, NotNull, BIGINT(19), FK to LABEL_TYPE} <br />
     * @return The value of the column 'LABEL_TYPE_ID'. (basically NotNull if selected: for the constraint)
     */
    public Long getLabelTypeId() {
        return _labelTypeId;
    }

    /**
     * [set] LABEL_TYPE_ID: {IX, NotNull, BIGINT(19), FK to LABEL_TYPE} <br />
     * @param labelTypeId The value of the column 'LABEL_TYPE_ID'. (basically NotNull if update: for the constraint)
     */
    public void setLabelTypeId(final Long labelTypeId) {
        __modifiedProperties.addPropertyName("labelTypeId");
        _labelTypeId = labelTypeId;
    }
}
