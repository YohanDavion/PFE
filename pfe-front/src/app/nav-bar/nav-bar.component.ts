import {Component} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {MenubarModule} from 'primeng/menubar';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-nav-bar',
  imports: [MenubarModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss'
})
export class NavBarComponent {
  items: MenuItem[] | undefined = [];
  role : string | null | undefined;

  constructor(private loginService : AuthService) {
  }

  isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof localStorage !== 'undefined';
  }


  ngOnInit() {
    if (this.isBrowser()) {
      this.role = localStorage.getItem('user_role');
    }

    if (!this.role) {
      this.items = [
        {
          label: 'Compte',
          icon: 'pi pi-user',
          items: [
            {
              label: 'Se Connecter',
              icon: 'pi pi-sign-in',
              routerLink: ['/login'],
              routerLinkActiveOptions: {
                exact: true
              }
            }
          ]
        }
      ];
    } else {
      this.items = [
        ...(this.role === 'ORTHOPHONISTE' || this.role === 'ADMINISTRATEUR' ? [
          {
            label: 'Séries et Animations',
            icon: 'pi pi-list-check',
            items: [
              { label: 'Liste Séries', icon: 'pi pi-list', routerLink: ['/list-series'] },
              { label: 'Liste Animations', icon: 'pi pi-list', routerLink: ['/list-animations'] },
              { label: 'Créer Séries', icon: 'pi pi-plus-circle', routerLink: ['/create-series'] },
              { label: 'Créer Animation', icon: 'pi pi-plus-circle', routerLink: ['/create-animation'] },
            ]
          }
        ] : []),

        ...(this.role === 'ORTHOPHONISTE' || this.role === 'ADMINISTRATEUR' ? [
          {
            label: 'Patients',
            icon: 'pi pi-face-smile',
            items: [
              { label: 'Liste Patients', icon: 'pi pi-users', routerLink: ['/list-patients'] },
              { label: 'Créer Patients', icon: 'pi pi-user-plus', routerLink: ['/create-patient'] },
              { separator: true },
              { label: 'Données Patients', icon: 'pi pi-chart-bar', routerLink: ['/data-patients'] },
            ]
          }
        ] : []),

        ...(this.role === 'ADMINISTRATEUR' ? [
          {
            label: 'Orthophonistes',
            icon: 'pi pi-users',
            routerLink: ['/list-orthophonistes']
          }
        ] : []),

        ...(this.role === 'PATIENT' ? [
          {
            label: 'Séries / Animations',
            icon: 'pi pi-play',
            routerLink: ['/list-series-patient'],
          }
        ] : []),

        {
          label: 'Comptes',
          icon: 'pi pi-user',
          items: [
            { label: 'Paramètres', icon: 'pi pi-cog', routerLink: ['/settings'] },
            {
              label: 'Se Connecter',
              icon: 'pi pi-sign-in',
              routerLink: ['/login'],
              routerLinkActiveOptions: {
                exact: true
              }
            },
            { separator: true },
            { label: 'Déconnexion', icon: 'pi pi-power-off', command: () => this.loginService.logout() },
          ]
        }
      ];
    }

    // this.items = [
    //   ...(this.role === 'ORTHOPHONISTE' || this.role === 'ADMINISTRATEUR' ? [
    //     {
    //       label: 'Séries et Animations',
    //       icon: 'pi pi-list-check',
    //       items: [
    //         { label: 'Liste Séries', icon: 'pi pi-list', routerLink: ['/list-series'] },
    //         { label: 'Liste Animations', icon: 'pi pi-list', routerLink: ['/list-animations'] },
    //         { label: 'Créer Séries', icon: 'pi pi-plus-circle', routerLink: ['/create-series'] },
    //         { label: 'Créer Animation', icon: 'pi pi-plus-circle', routerLink: ['/create-animation'] },
    //       ]
    //     }
    //   ] : []),
    //
    //   ...(this.role === 'ORTHOPHONISTE' || this.role === 'ADMINISTRATEUR' ? [
    //     {
    //       label: 'Patients',
    //       icon: 'pi pi-face-smile',
    //       items: [
    //         { label: 'Liste Patients', icon: 'pi pi-users', routerLink: ['/list-patients'] },
    //         { label: 'Créer Patients', icon: 'pi pi-user-plus', routerLink: ['/create-patient'] },
    //         { separator: true },
    //         { label: 'Données Patients', icon: 'pi pi-chart-bar', routerLink: ['/data-patients'] },
    //       ]
    //     }
    //   ] : []),
    //
    //   ...(this.role === 'ADMINISTRATEUR' ? [
    //     {
    //       label: 'Orthophonistes',
    //       icon: 'pi pi-users',
    //       routerLink: ['/list-orthophonistes']
    //     }
    //   ] : []),
    //
    //   ...(this.role === 'PATIENT' ? [
    //     {
    //       label: 'Séries / Animations',
    //       icon: 'pi pi-play',
    //       routerLink: ['/list-series-patient'],
    //     }
    //   ] : []),
    //
    //   {
    //     label: 'Comptes',
    //     icon: 'pi pi-user',
    //     items: [
    //       { label: 'Paramètres', icon: 'pi pi-cog', routerLink: ['/settings'] },
    //       {
    //         label: 'Se Connecter',
    //         icon: 'pi pi-sign-in',
    //         routerLink: ['/login'],
    //         routerLinkActiveOptions: {
    //           exact: true
    //         }
    //       },
    //       { separator: true },
    //       { label: 'Déconnexion', icon: 'pi pi-power-off', command: () => this.loginService.logout() },
    //     ]
    //   }
    // ];
  }
}
