package com.xyoye.dandanplay.utils.database.builder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.xyoye.dandanplay.app.IApplication;
import com.xyoye.dandanplay.utils.database.DataBaseInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.CheckReturnValue;

/**
 * Created by xyoye on 2019/4/17.
 */
public class UpdateBuilder{
    private SQLiteDatabase sqLiteDatabase;
    private int tablePosition;
    private ContentValues mValues;
    private List<String> whereClause;
    private List<String> whereArgs;

    UpdateBuilder(int tablePosition, SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase = sqLiteDatabase;
        this.tablePosition = tablePosition;
        mValues = new ContentValues();
        whereArgs = new ArrayList<>();
        whereClause = new ArrayList<>();
    }

    @CheckReturnValue
    public UpdateBuilder where(int column, String value) {
        String whereClauseText = DataBaseInfo.getFieldNames()[tablePosition][column] + " = ?";
        whereClause.add(whereClauseText);
        whereArgs.add(value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, String value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, Byte value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, Short value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, Integer value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, Long value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, Float value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, Double value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, Boolean value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    @CheckReturnValue
    public UpdateBuilder param(int column, byte[] value) {
        mValues.put(DataBaseInfo.getFieldNames()[tablePosition][column], value);
        return this;
    }

    public int execute(){
        if (mValues == null)
            return 0;

        // clauseList -> "clause1 = ? AND clause2 = ?"
        String clause;
        String[] args = new String[whereClause.size()];
        StringBuilder clauseBuilder = new StringBuilder();
        for (int i = 0; i < whereClause.size(); i++) {
            clauseBuilder.append(whereClause.get(i)).append(" AND ");
            args[i] = whereArgs.get(i);
        }
        if (clauseBuilder.length() > 5){
            clause = clauseBuilder.substring(0, clauseBuilder.length()-5);
        }else {
            clause = "";
        }
        return sqLiteDatabase.update(DataBaseInfo.getTableNames()[tablePosition], mValues, clause , args);
    }

    public void postExecute(){
        IApplication.getExecutor().execute(this::execute);
    }
}