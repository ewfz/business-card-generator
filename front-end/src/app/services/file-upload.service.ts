import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  upload(file: File,user: User): Observable<any> {
    const formData: FormData = new FormData();

    const data = new Blob([JSON.stringify({
      "nom": user.nom,
   "nomArabe": user.nomArabe,
   "prenom": user.prenom,
   "prenomArabe": user.prenomArabe,
   "cin": user.cin,
   "profession": user.profession,
   "dateDeNaissance": user.dateDeNaissance,
   "typeCarte": user.typeCarte,
   "adresse": user.adresse
})], {
 type: 'application/json'
})

    formData.append('file', file);
    formData.append('user',data);

    return this.http.post(`${this.baseUrl}/users`, formData , { responseType: 'blob' });

  }

}
