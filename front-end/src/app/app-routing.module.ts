import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PageNotFoundComponent} from "./core/page-not-found/page-not-found.component";
import {adminGuard} from "./core/guards/admin.guard";
import {isAuthenticatedGuard} from "./core/guards/is-authenticated.guard";
import {adminMatchGuard} from "./core/guards/admin-match.guard";
import {userMatchGuard} from "./core/guards/user-match.guard";

const routes: Routes = [
  { path: 'admin',
    loadChildren: () => import ('./modules/admin/admin.module').then(value => value.AdminModule),
    canMatch: [adminMatchGuard],
    canActivate: [adminGuard, isAuthenticatedGuard],

  },
  { path: 'user',
    loadChildren: () => import ('./modules/user/user.module').then(value => value.UserModule),
    canMatch: [userMatchGuard],
    canActivate: [isAuthenticatedGuard]
  },
  { path: 'home',
    loadChildren: () => import ('./modules/home/home.module').then(value => value.HomeModule)
  },
  {path: '', redirectTo: '/home', pathMatch: "full"},
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
