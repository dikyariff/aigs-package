package com.aigs.base.ui.navigations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aigs.base.common.AppConstants.Route
import com.aigs.base.ui.screens.createaccount.CreateAccountView
import com.aigs.base.ui.screens.createaccount.CreateAccountViewModel
import com.aigs.base.ui.screens.home.HomeView
import com.aigs.base.ui.screens.home.HomeViewModel
import com.aigs.base.ui.screens.login.LoginView
import com.aigs.base.ui.screens.login.LoginViewModel
import com.aigs.base.ui.screens.onboarding.OnboardingView
import com.aigs.base.ui.screens.onboarding.OnboardingViewModel
import com.aigs.base.ui.screens.splash.SplashViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val splashViewModel: SplashViewModel = koinViewModel()
    val initialRoute by splashViewModel.initialRoute.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = initialRoute ?: Route.ONBOARDING,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable(Route.ONBOARDING) {
            val viewModel: OnboardingViewModel = getViewModel()
            OnboardingView(navController, viewModel)
        }
        composable(Route.LOGIN) {
            val viewModel: LoginViewModel = getViewModel()
            LoginView(navController, viewModel)
        }
        composable(Route.CREATE_ACCOUNT) {
            val viewModel: CreateAccountViewModel = getViewModel()
            CreateAccountView(navController, viewModel)
        }
        composable(Route.HOME) {
            val viewModel: HomeViewModel = getViewModel()
            HomeView(navController, viewModel)
        }
    }
}