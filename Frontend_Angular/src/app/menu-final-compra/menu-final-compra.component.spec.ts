import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuFinalCompraComponent } from './menu-final-compra.component';

describe('MenuFinalCompraComponent', () => {
  let component: MenuFinalCompraComponent;
  let fixture: ComponentFixture<MenuFinalCompraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuFinalCompraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuFinalCompraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
