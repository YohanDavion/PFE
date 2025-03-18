import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';

@Component({
  selector: 'app-nav-bar',
  imports: [MenubarModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss'
})
export class NavBarComponent {
  items: MenuItem[] | undefined;

    ngOnInit() {
        this.items = [
            {
                label: 'Séries',
                icon: 'pi pi-list-check',
                items: [
                    {
                        label: 'Liste Séries',
                        icon: 'pi pi-list',
                        routerLink: ['/list-series'],
                        routerLinkActiveOptions: {
                            exact: true
                        }
                    },
                    {
                        label: 'Créer Séries',
                        icon: 'pi pi-plus-circle',
                        routerLink: ['/create-series'],
                        routerLinkActiveOptions: {
                            exact: true
                        }
                    }
                ],
            },
            {
                label: 'Patients',
                icon: 'pi pi-face-smile',
                items: [
                    {
                        label: 'Liste Patients',
                        icon: 'pi pi-users',
                        routerLink: ['/list-patients'],
                        routerLinkActiveOptions: {
                            exact: true
                        }
                    },
                    {
                        label: 'Créer Patients',
                        icon: 'pi pi-user-plus',
                        routerLink: ['/create-user'],
                        routerLinkActiveOptions: {
                            exact: true
                        }
                    },
                    {
                        separator: true,
                    },
                    {
                        label: 'Données Patients',
                        icon: 'pi pi-chart-bar',
                        routerLink: ['/data-patients'],
                        routerLinkActiveOptions: {
                            exact: true
                        }
                    },
                ],
            },
            {
                label: 'Orthophonistes',
                icon: 'pi pi-users',
                routerLink: ['/list-orthophonistes'],
                routerLinkActiveOptions: {
                  exact: true
                }
            },
            {
                label: 'Comptes',
                icon: 'pi pi-user',
                items: [
                    {
                        label: 'Se Connecter',
                        icon: 'pi pi-sign-in',
                        routerLink: ['/login'],
                        routerLinkActiveOptions: {
                        exact: true
                    }
                    },
                    {
                        label: 'Paramètres',
                        icon: 'pi pi-cog',
                    },
                    {
                        separator: true,
                    },
                    {
                        label: 'Déconnexion',
                        icon: 'pi pi-power-off',
                    },
                ],
            },
        ]
    }
}
