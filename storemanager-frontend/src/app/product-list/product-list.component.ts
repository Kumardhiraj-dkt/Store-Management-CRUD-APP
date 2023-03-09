import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] | undefined;
  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.getProducts();
  }

  private getProducts(){
    this.productService.getProductList().subscribe(data => {
      this.products = data;
    });
  }

  updateProduct(id: number|undefined){
    this.router.navigate(['update-product',id]);
  }

  deleteProduct(id: number|undefined){
    this.productService.deleteProduct(id).subscribe(data=>{
      console.log(data);
      this.getProducts();
    })
  }
}
