/*
 * This file is generated by jOOQ.
 */
package com.devstaff.farm_collector.repositories.jooq;


import com.devstaff.farm_collector.repositories.jooq.tables.Crops;
import com.devstaff.farm_collector.repositories.jooq.tables.FarmFields;
import com.devstaff.farm_collector.repositories.jooq.tables.FarmOperations;
import com.devstaff.farm_collector.repositories.jooq.tables.Farmers;
import com.devstaff.farm_collector.repositories.jooq.tables.Farms;
import com.devstaff.farm_collector.repositories.jooq.tables.records.CropsRecord;
import com.devstaff.farm_collector.repositories.jooq.tables.records.FarmFieldsRecord;
import com.devstaff.farm_collector.repositories.jooq.tables.records.FarmOperationsRecord;
import com.devstaff.farm_collector.repositories.jooq.tables.records.FarmersRecord;
import com.devstaff.farm_collector.repositories.jooq.tables.records.FarmsRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CropsRecord> CROPS_NAME_KEY = Internal.createUniqueKey(Crops.CROPS, DSL.name("crops_name_key"), new TableField[] { Crops.CROPS.NAME }, true);
    public static final UniqueKey<CropsRecord> CROPS_PKEY = Internal.createUniqueKey(Crops.CROPS, DSL.name("crops_pkey"), new TableField[] { Crops.CROPS.ID }, true);
    public static final UniqueKey<FarmFieldsRecord> FARM_FIELDS_PKEY = Internal.createUniqueKey(FarmFields.FARM_FIELDS, DSL.name("farm_fields_pkey"), new TableField[] { FarmFields.FARM_FIELDS.ID }, true);
    public static final UniqueKey<FarmOperationsRecord> FARM_OPERATIONS_PKEY = Internal.createUniqueKey(FarmOperations.FARM_OPERATIONS, DSL.name("farm_operations_pkey"), new TableField[] { FarmOperations.FARM_OPERATIONS.ID }, true);
    public static final UniqueKey<FarmersRecord> FARMERS_PKEY = Internal.createUniqueKey(Farmers.FARMERS, DSL.name("farmers_pkey"), new TableField[] { Farmers.FARMERS.ID }, true);
    public static final UniqueKey<FarmsRecord> FARMS_PKEY = Internal.createUniqueKey(Farms.FARMS, DSL.name("farms_pkey"), new TableField[] { Farms.FARMS.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<FarmFieldsRecord, FarmsRecord> FARM_FIELDS__FK_FARM_FIELDS = Internal.createForeignKey(FarmFields.FARM_FIELDS, DSL.name("fk_farm_fields"), new TableField[] { FarmFields.FARM_FIELDS.FARM_ID }, Keys.FARMS_PKEY, new TableField[] { Farms.FARMS.ID }, true);
    public static final ForeignKey<FarmOperationsRecord, CropsRecord> FARM_OPERATIONS__FK_FARM_OPERATIONS_CROPS = Internal.createForeignKey(FarmOperations.FARM_OPERATIONS, DSL.name("fk_farm_operations_crops"), new TableField[] { FarmOperations.FARM_OPERATIONS.CROP_ID }, Keys.CROPS_PKEY, new TableField[] { Crops.CROPS.ID }, true);
    public static final ForeignKey<FarmOperationsRecord, FarmFieldsRecord> FARM_OPERATIONS__FK_FARM_OPERATIONS_FIELDS = Internal.createForeignKey(FarmOperations.FARM_OPERATIONS, DSL.name("fk_farm_operations_fields"), new TableField[] { FarmOperations.FARM_OPERATIONS.FIELD_ID }, Keys.FARM_FIELDS_PKEY, new TableField[] { FarmFields.FARM_FIELDS.ID }, true);
    public static final ForeignKey<FarmsRecord, FarmersRecord> FARMS__FK_FARMERS_FARM = Internal.createForeignKey(Farms.FARMS, DSL.name("fk_farmers_farm"), new TableField[] { Farms.FARMS.FARMER_ID }, Keys.FARMERS_PKEY, new TableField[] { Farmers.FARMERS.ID }, true);
}
