import { Component } from '@angular/core';

@Component({
  selector: 'app-list-patients',
  imports: [],
  templateUrl: './list-patients.component.html',
  styleUrl: './list-patients.component.scss'
})
export class ListPatientsComponent {
  /*clients: ClientModel[] = [];

  constructor(
    private banqueService: BanqueService,
    private router: Router,
    private messageService: MessageService) { };

  ngOnInit(): void {
    this.getClient();
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  goToDetail(pageName:string,client:ClientModel){
    this.router.navigate(['detail/client'],{state:{client}});
  }

  deleteClient(client: ClientModel){
    this.banqueService.deleteClient(client.id).subscribe({
      next: response => {
        this.showSuccess();
      },
      error: error => {
        this.showError();
      }
    });
  }

  getClient(){
    this.banqueService.getAllClients().subscribe(data => {
      this.clients = data.map((client: ClientModel) => ({
        ...client,
        name: client.firstname + ' ' + client.lastname,
      }))});
  }

  showSuccess() {
    this.getClient();
    this.messageService.add({ severity: 'success', summary: 'Succés', detail: 'Suppréssion du Client' });
  }
  showError() {
    this.messageService.add({ severity: 'error', summary: 'Echec', detail: 'Echec de la suppréssion du Client' });
}*/
}
