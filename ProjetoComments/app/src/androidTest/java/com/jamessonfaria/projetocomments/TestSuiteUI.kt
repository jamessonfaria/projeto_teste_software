package com.jamessonfaria.projetocomments

import com.jamessonfaria.projetocomments.activity.CreateCommentsTelaTest
import com.jamessonfaria.projetocomments.activity.DetailCommentTelaTest
import com.jamessonfaria.projetocomments.activity.ListCommentsTelaTest
import com.jamessonfaria.projetocomments.activity.LoginTelaTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
        LoginTelaTest::class,
        ListCommentsTelaTest::class,
        CreateCommentsTelaTest::class,
        DetailCommentTelaTest::class
)
class TestSuiteUI










