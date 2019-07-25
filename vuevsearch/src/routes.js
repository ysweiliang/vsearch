import Index from './components/Index'
import Search from './components/Search'

const routes = [
    {path: '/', component: Index, name: '1'},
    {
        path: '/search',
        component: Search,
        name: '2',
        props: true
    }
];

export default routes