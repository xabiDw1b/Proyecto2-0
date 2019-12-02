package com.aar.app.proyectoLlodio.sopaLetras.custom;

/**
 * Created by xabier on 28/06/17.
 *
 * Sample data adapter (for preview in android studio visual editor)
 */

final class SampleLetterGridDataAdapter extends LetterGridDataAdapter {

    private int mRowCount;
    private int mColCount;

    SampleLetterGridDataAdapter(int rowCount, int colCount) {
        mRowCount = rowCount;
        mColCount = colCount;
    }

    @Override
    public int getColCount() {
        return mColCount;
    }

    @Override
    public int getRowCount() {
        return mRowCount;
    }

    @Override
    public char getLetter(int row, int col) {
        return 'A';
    }
}
