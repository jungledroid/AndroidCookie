package com.joez.presenter;

import android.view.View;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by JoeZ on 2016/5/27.
 */
public class AnimatePresenterTest {

    @Mock
    AnimatePresenter.AnimateCallback mCallback;
    private AnimatePresenter mPresenter;

    @Before
    public void setupPresenter(){
        MockitoAnnotations.initMocks(this);
        mPresenter = new AnimatePresenter(any(View.class));
        mPresenter.setAnimateCallback(mCallback);
    }

    @Test
    public void test_aniamteWithFlag(){
        //multimode
        mPresenter.animateWithMode(AnimatePresenter.STATE_MODE_MULTI_MASK);

        mPresenter.animateImageByFlag(AnimatePresenter.ROTATION_MASK);
        ArgumentCaptor<Float> rotaion = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeast(1)).rotationView(rotaion.capture());
        assertEquals((Float) 30.0f,rotaion.getValue());

        mPresenter.animateImageByFlag(AnimatePresenter.TRANSITIONX_MASK);
        ArgumentCaptor<Float> traistionX = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeast(1)).translationXView(traistionX.capture());
        assertEquals((Float) 200f,traistionX.getValue());

        mPresenter.animateImageByFlag(AnimatePresenter.TRANSITIONY_MASK);
        ArgumentCaptor<Float> traistionY = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeast(1)).translationYView(traistionY.capture());
        assertEquals((Float) 200f,traistionY.getValue());

    }

    @Test
    public void test_animateMultiFlag(){
        mPresenter.animateWithMode(AnimatePresenter.STATE_MODE_MULTI_MASK);
        mPresenter.animateImageByFlag(AnimatePresenter.ROTATION_MASK|AnimatePresenter.TRANSITIONX_MASK);
        ArgumentCaptor<Float> traistionX = ArgumentCaptor.forClass(Float.class);
        ArgumentCaptor<Float> rotaion = ArgumentCaptor.forClass(Float.class);

        InOrder inorder = inOrder(mCallback);
        inorder.verify(mCallback,atLeastOnce()).rotationView(rotaion.capture());
        assertEquals((Float) 30f,rotaion.getValue());
        inorder.verify(mCallback,atLeastOnce()).translationXView(traistionX.capture());
        assertEquals((Float)200f,traistionX.getValue());
        inorder.verify(mCallback,never()).translationYView(any(Float.class));
    }

    @Test
    public void test_Fillafteremode_Flag(){
        mPresenter.clearAllSate();
        mPresenter.animateWithMode(AnimatePresenter.STATE_MODE_FILAFTER_MASK);
        mPresenter.animateImageByFlag(AnimatePresenter.ROTATION_MASK);
        ArgumentCaptor<Float> rotaion = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeastOnce()).rotationView(rotaion.capture());
        assertEquals((Float) 30.0f,rotaion.getValue());
        mPresenter.animateImageByFlag(AnimatePresenter.ROTATION_MASK);
        ArgumentCaptor<Float> rotaion2 = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeastOnce()).rotationView(rotaion2.capture());
        assertEquals((Float) 0.0f,rotaion2.getValue());
    }

    @Test
    public void test_singlemode_flag(){
        mPresenter.clearAllSate();
        mPresenter.animateWithMode(AnimatePresenter.STATE_MODE_SINGLE_MASK);
        mPresenter.animateImageByFlag(AnimatePresenter.ROTATION_MASK);
        ArgumentCaptor<Float> rotaion = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeastOnce()).rotationView(rotaion.capture());
        assertEquals((Float) 30.0f,rotaion.getValue());

        mPresenter.animateImageByFlag(AnimatePresenter.ROTATION_MASK);
        ArgumentCaptor<Float> rotaion2 = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeastOnce()).rotationView(rotaion2.capture());
        assertEquals((Float) 30.0f,rotaion2.getValue());

        mPresenter.animateWithMode(AnimatePresenter.STATE_MODE_SINGLE_MASK);
        mPresenter.animateImageByFlag(AnimatePresenter.TRANSITIONX_MASK);
        ArgumentCaptor<Float> transitionX = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeastOnce()).translationXView(transitionX.capture());
        assertEquals((Float)200f,transitionX.getValue());
        ArgumentCaptor<Float> rotaion3 = ArgumentCaptor.forClass(Float.class);
        verify(mCallback,atLeastOnce()).rotationView(rotaion3.capture());
        assertEquals((Float)0f,rotaion3.getValue());
    }
}
