import React, { PureComponent } from 'react';
import Item from './Item/Item.js'
import PropTypes from 'prop-types';
//import { Test } from './Products.styles';

const products = [
  {id : 1, name : 'Fan1'},
  {id : 2, name : 'Fan2'},
  {id : 3, name : 'Fan3'},
  {id : 4, name : 'Fan4'},
  {id : 5, name : 'Fan5'},
  {id : 6, name : 'Fan6'},
  {id : 7, name : 'Fan7'},
  {id : 8, name : 'Fan8'}
];

class Products extends PureComponent { 
  constructor(props) {
    super(props);

    this.state = {
      hasError: false,
      products : products
    };
  }

  componentWillMount = () => {
    console.log('Products will mount');
  }

  componentDidMount = () => {
    console.log('Products mounted');
  }

  componentWillReceiveProps = (nextProps) => {
    console.log('Products will receive props', nextProps);
  }

  componentWillUpdate = (nextProps, nextState) => {
    console.log('Products will update', nextProps, nextState);
  }

  componentDidUpdate = () => {
    console.log('Products did update');
  }

  componentWillUnmount = () => {
    console.log('Products will unmount');
  }

  render () {
    if (this.state.hasError) {
      return <h1>Something went wrong.</h1>;
    }

    const {products} = this.state;

    return (
      <div className="ProductsWrapper">
        <Item items={products}/>
      </div>
    );
  }
}

Products.propTypes = {
  // bla: PropTypes.string,
};

Products.defaultProps = {
  // bla: 'test',
};

export default Products;
