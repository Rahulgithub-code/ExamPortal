import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './pages/admin/add-category/add-category.component';
import { AddQuestionComponent } from './pages/admin/add-question/add-question.component';
import { AddQuizComponent } from './pages/admin/add-quiz/add-quiz.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { UpdateQuizComponent } from './pages/admin/update-quiz/update-quiz.component';
import { ViewCategoryComponent } from './pages/admin/view-category/view-category.component';
import { ViewQuestionComponent } from './pages/admin/view-question/view-question.component';
import { ViewQuizzesComponent } from './pages/admin/view-quizzes/view-quizzes.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { SignupComponent } from './pages/signup/signup.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { AdminGuard } from './services/admin.guard';
import { NormalGuard } from './services/normal.guard';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    canActivate:[NormalGuard]
  },
  {
    path: "signup", 
    component: SignupComponent,
  },
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'admin',
    component:AdminDashboardComponent,
    canActivate:[AdminGuard],
    children:[
      {
        path:'',
        component: WelcomeComponent
      },
      {
        path:'profile',
        component:ProfileComponent
      },
      {
        path:'categories',
        component:ViewCategoryComponent
      },
      {
        path:'add-category',
        component:AddCategoryComponent
      },
      {
        path:'quizzes',
        component:ViewQuizzesComponent
      },
      {
        path:'add-quiz',
        component:AddQuizComponent
      },
      {
        path:'quiz/:qid',
        component: UpdateQuizComponent
      },
      {
        path:'view-questions/:qid/:title',
        component:ViewQuestionComponent
      },
      {
        path:'add-question/:qid/:title',
        component: AddQuestionComponent
      }
    ]
  },
  {
    path:'user-dashboard',
    component:UserDashboardComponent,
    pathMatch:'full',
    canActivate: [NormalGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
